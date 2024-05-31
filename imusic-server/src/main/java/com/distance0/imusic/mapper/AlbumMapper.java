package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.vo.AlbumVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 专辑分页
     * @param album
     * @return
     */
    Page<Album> pageQuery(Album album);

    /**
     * 查找专辑数组
     * @param album
     * @return
     */
    List<Album> getAlbumList(Album album);

    /**
     *
     * @param album
     */
    void update(Album album);

    /**
     * 获取随机专辑
     * @return
     */
    @Select("select * from album where status = 1 order by RAND() LIMIT 20")
    List<AlbumVo> getRandomAlbum();
}
