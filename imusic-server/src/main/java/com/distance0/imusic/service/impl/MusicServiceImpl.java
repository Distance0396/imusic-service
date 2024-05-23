package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.dto.MusicSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.FindNullException;
import com.distance0.imusic.exception.StatusException;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.MusicService;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:36
 * @description:
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 音乐分页
     * @param dto
     * @return
     */
    @Override
    public PageResult page(MusicPageDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        Page<Music> page = musicMapper.pageQuery(dto);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public void changeStatus(Integer status, List<Long> id) {
        int newStatus;
        if (status.equals(StatusConstant.DISABLE)){
            newStatus = StatusConstant.DISABLE;
        }else if (status.equals(StatusConstant.ENABLE)){
            newStatus = StatusConstant.ENABLE;
        } else {
            throw new StatusException(MessageConstant.STATUS_EXCEPTION);
        }

        for(Long x : id){
            Music music = new Music();
            music.setId(x);
            music.setStatus(newStatus);
            musicMapper.updateSinger(music);
        }
    }

    /**
     * 添加音乐
     * @param dto
     * @return
     */
    @Override
    public void save(MusicSaveDto dto) {
        Singer build = Singer.builder()
                .name(dto.getSingerName())
                .build();
        Singer singer = singerMapper.getSinger(build);

        if (singer != null){
            Album album = Album.builder()
                    .id(dto.getAlbumId()).build();
            Album albumByAlbum = albumMapper.getAlbumByAlbum(album);


            Music music = Music.builder()
                    .singerId(singer.getId())
                    .albumName(albumByAlbum.getName())
                    .audio(dto.getAudio())
                    .createTime(LocalDateTime.now())
                    .status(StatusConstant.ENABLE).build();
            BeanUtils.copyProperties(dto, music);
            musicMapper.insert(music);
            return;
        }

        throw new FindNullException(MessageConstant.FIND_SINGER_NULL);

    }
}
