package com.distance0.imusic.service.impl;

import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.MusicFormMusic;
import com.distance0.imusic.mapper.*;
import com.distance0.imusic.service.MusicFormService;
import com.distance0.imusic.utils.ImagesUtil;
import com.distance0.imusic.vo.MusicFormVo;
import com.distance0.imusic.vo.MusicImageVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:42
 * @description:
 */
@Slf4j
@Service
public class MusicFormServiceImpl implements MusicFormService {

    @Autowired
    private MusicFormMapper musicFormMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private CollectFormMapper collectFormMapper;

    @Autowired
    private MusicFormMusicMapper musicFormMusicMapper;
    @Autowired
    private MusicMapper musicMapper;

    /**
     * 根据用户id查询收藏歌单
     *
     * @return
     */
//    @Override
//    public List<MusicFormVo> getMusicFormByUserId() {
//        return musicFormMapper.getMusicFormByUser(BaseContext.getContextId());
//    }

    /**
     * 根据歌单id查询歌单
     *
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "musicForm", key = "#root.methodName + '[' + #id + ']'")
    @Override
    public MusicFormVo getMusicFormById(Long id) {
        MusicFormVo musicFormVo = musicFormMapper.getMusicFormById(id);
        List<MusicImageVo> formMusic = musicFormMapper.getFormMusic(musicFormVo.getId());

        List<MusicImageVo> collect = formMusic.stream().map(music -> {

            if (music.getImage() == null) {
                String imageBySingerId = singerMapper.getImageBySingerId(music.getSingerId());
                music.setImage(imageBySingerId);
            }
//            if (music.getAlbumId() != null) {
//                String musicImageById = albumMapper.getImageById(music.getAlbumId());
//                music.setImage(musicImageById);
//            }
//            else if (music.getSingerId() != null) {
//                String imageBySingerId = singerMapper.getImageBySingerId(music.getSingerId());
//                music.setImage(imageBySingerId);
//            }
            return music;
        }).collect(Collectors.toList());
        musicFormVo.setMusicList(collect);
        log.info(String.valueOf(collect));
        return musicFormVo;
    }

    /**
     * 新增歌单
     * @return
     */
    @Override
    public void addMusicForm() {
        Integer musicFormByUser = musicFormMapper.getMusicFormByUser(BaseContext.getContextId());
        int name = musicFormByUser + 1;
        MusicForm build = MusicForm.builder()
                .name("我的 #" + name + "歌单")
                .userId(BaseContext.getContextId())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        musicFormMapper.addMusicForm(build);
        collectFormMapper.addCollectForm(BaseContext.getContextId(), build.getId());
    }

    /**
     * 添加歌曲至歌单
     *
     * @param musicFormMusic
     * @return
     */
    @Override
    public void appendMusicForm(MusicFormMusic musicFormMusic) {
        MusicFormMusic musicFormMusic1 = musicFormMusicMapper.findById(musicFormMusic);
        if (musicFormMusic1 != null) {
            return;
        }

        Music music = musicMapper.getMusic(Music.builder().id(musicFormMusic.getMusicId()).build());
        if (music.getImage() != null) {
            CompletableFuture.supplyAsync(() -> {
                try {
                    return ImagesUtil.readImages(music.getImage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).thenAccept(color -> {
                musicFormMapper.update(MusicForm
                        .builder()
                        .id(musicFormMusic.getMusicFormId())
                        .image(music.getImage())
                        .color(color)
                        .build());
                musicFormMusicMapper.save(musicFormMusic);
            }).exceptionally(ex -> {
                log.info(ex.getMessage());
                return null;
            });
        } else {
            musicFormMusicMapper.save(musicFormMusic);
        }
    }

    /**
     * 删除歌单歌曲
     *
     * @param musicFormMusic
     * @return
     */
    @Override
    public void deleteMusic(MusicFormMusic musicFormMusic) {
        MusicFormMusic music = musicFormMusicMapper.findById(musicFormMusic);
        if (music != null) {
            musicFormMusicMapper.deleteMusic(musicFormMusic);
        }
    }
}
