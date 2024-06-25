package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.MusicFormMusic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: XiangJing
 * @date: 2024/6/10 下午10:02
 * @description:
 */
@Mapper
public interface MusicFormMusicMapper {
    /**
     * 添加歌曲至歌单
     * @param musicFormMusic
     * @return
     */
    @Insert("insert into music_form_music (music_form_id, music_id) VALUES (#{musicFormId},#{musicId})")
    void save(MusicFormMusic musicFormMusic);

    /**
     * 查询用户歌单歌曲
     * @param musicFormMusic
     * @return
     */
    MusicFormMusic findById(MusicFormMusic musicFormMusic);

    /**
     * 删除歌单歌曲
     * @param musicFormMusic
     * @return
     */
    @Delete("delete from music_form_music where music_form_id = #{musicFormId} and music_id = #{musicId}")
    void deleteMusic(MusicFormMusic musicFormMusic);
}
