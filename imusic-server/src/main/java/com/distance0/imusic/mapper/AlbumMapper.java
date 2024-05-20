package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:27
 * @description:
 */
@Mapper
public interface AlbumMapper {
    /**
     * 添加专辑
     * @param album
     */
    void insert(Album album);

    /**
     * 获取专辑
     * @param album
     * @return
     */
    Album getAlbumByAlbum(Album album);
}
