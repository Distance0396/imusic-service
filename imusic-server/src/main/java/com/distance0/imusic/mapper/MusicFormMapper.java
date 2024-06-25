package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.vo.MusicFormVo;
import com.distance0.imusic.vo.MusicImageVo;
import com.distance0.imusic.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:41
 * @description:
 */
@Mapper
public interface MusicFormMapper {
    /**
     * 根据用户id查询收藏歌单
     * @param id
     * @return
     */
    @Select("select * from music_form where user_id = #{id}")
    List<MusicForm> getMusicFormByUser(Long id);

    /**
     * 根据歌单id查询歌单包含歌曲
     * @param id
     * @return
     */
    MusicFormVo getMusicFormById(Long id);

    /**
     * 根据歌单id查询歌曲
     * @param id
     * @return
     */
    List<MusicImageVo> getFormMusic(Long id);

    /**
     * 新增歌单
     * @return
     */
    Long addMusicForm(MusicForm musicForm);

    /**
     * 修改歌单
     * @param musicForm
     */
    void update(MusicForm musicForm);

    /**
     * 根据歌单id和用户id查询
     * @param id
     * @return
     */
    @Select("select * from music_form where user_id = #{userId} and id = #{id}")
    MusicForm findMusicFormByUserIdAndFormId(Long id, Long userId);
}
