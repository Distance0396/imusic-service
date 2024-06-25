package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.dto.MusicFormDto;
import com.distance0.imusic.entity.*;
import com.distance0.imusic.exception.BaseException;
import com.distance0.imusic.exception.FindNullException;
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
    public void addMusicForm(Long id) {
        List<MusicForm> musicFormList = musicFormMapper.getMusicFormByUser(id);
        int name = musicFormList.size() + 1;
        MusicForm build = MusicForm.builder()
                .name("我的 #" + name + "歌单")
                .userId(id)
                .status(StatusConstant.ENABLE)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        musicFormMapper.addMusicForm(build);

        collectFormMapper.addCollectForm(CollectForm
                .builder()
                .userId(BaseContext.getContextId())
                .musicFormId(build.getId())
                .build());
    }

    /**
     * 添加歌曲至歌单
     *
     * @param musicFormMusic
     * @return
     */
    @Override
    public void appendMusicForm(MusicFormMusic musicFormMusic) {
        /*
            判断歌单是否为用户自己的歌单
            不为用户自己的不能添加
         */
        MusicForm musicFormByUserIdAndFormId = musicFormMapper.findMusicFormByUserIdAndFormId(musicFormMusic.getMusicFormId(), BaseContext.getContextId());
        if (musicFormByUserIdAndFormId == null) {
            throw new BaseException(MessageConstant.NOT_BELONG_MUSIC_FORM);
        }

        /*
            判断歌曲是否已存在歌单
         */
        MusicFormMusic musicFormMusic1 = musicFormMusicMapper.findById(musicFormMusic);
        if (musicFormMusic1 != null) {
            return;
        }

        /*
            判断歌曲是否存在image存在就设置为歌单的封面
            没有封面就直接添加
         */
        Music music = musicMapper.getMusic(Music.builder().id(musicFormMusic.getMusicId()).build());
        if (music.getImage() != null) {
            musicFormMusicMapper.save(musicFormMusic);

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
        if (music == null) {
            throw new FindNullException(MessageConstant.MUSIC_FORM_NOT_FIND_MUSIC);
        }
        musicFormMusicMapper.deleteMusic(musicFormMusic);

    }

    /**
     * 修改歌单
     * @param dto
     * @return
     */
    @Override
    public void update(MusicFormDto dto) {
        /*
            根据歌单id和用户id查询是否有歌单
         */
        MusicForm musicForm = musicFormMapper.findMusicFormByUserIdAndFormId(dto.getId(), BaseContext.getContextId());
        if (musicForm == null){
            throw new FindNullException(MessageConstant.FIND_MUSIC_FORM_NULL);
        }
        /*
            先修改歌单
         */
        MusicForm musicForm1 = new MusicForm();
        BeanUtils.copyProperties(dto, musicForm1);
        musicFormMapper.update(musicForm1);

        /*
            前端有传输image便读取颜色
         */
        if (!dto.getImage().isEmpty()){
            CompletableFuture.runAsync(() -> {
                try {
                    MusicForm build = MusicForm.builder().color(ImagesUtil.readImages(dto.getImage())).build();
                    musicFormMapper.update(build);
                } catch (IOException e) {
                    throw new RuntimeException(MessageConstant.COLOR_READING_ERROR);
                }
            });
        }
    }
}
