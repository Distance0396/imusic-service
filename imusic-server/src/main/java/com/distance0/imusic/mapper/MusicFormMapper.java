package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.User;
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
    User getMusicFormByUser(Long id);

    /**
     * 根据歌单id查询歌单
     * @param id
     * @return
     */
    MusicForm getMusicFormById(Long id);
}
