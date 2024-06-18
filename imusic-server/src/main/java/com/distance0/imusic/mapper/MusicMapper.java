package com.distance0.imusic.mapper;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.entity.Music;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * @param Music
     * @return
     */
    Page<Music> pageQuery(Music Music);

    /**
     * 修改状态
     * @param music
     */
    void update(Music music);

    /**
     * 获取音乐数组
     * @param music
     * @return
     */
    List<Music> getMusicList(Music music);


    /**
     * 搜索
     * @param keyword
     * @return
     */
    @Select("select * from music where CONCAT(name, singer_name) like concat('%',#{keyword},'%')")
    List<Music> dimMusicByKeyword(String keyword);
}
