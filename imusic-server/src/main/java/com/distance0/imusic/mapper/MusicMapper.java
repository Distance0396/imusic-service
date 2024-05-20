package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.Music;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:32
 * @description:
 */
@Mapper
public interface MusicMapper {
    /**
     * 添加歌手
     * @param music
     */
    void insert(Music music);

    /**
     * 获取歌手
     * @param music
     * @return
     */
    Music getMusicByMusic(Music music);
}
