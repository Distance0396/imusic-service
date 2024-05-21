package com.distance0.imusic.mapper;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.entity.Music;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:32
 * @description:
 */
@Mapper
public interface MusicMapper {
    /**
     * 添加音乐
     * @param music
     */
    void insert(Music music);

    /**
     * 获取音乐
     * @param music
     * @return
     */
    Music getMusic(Music music);

    /**
     * 音乐分页
     * @param dto
     * @return
     */
    Page<Music> pageQuery(MusicPageDto dto);
}
