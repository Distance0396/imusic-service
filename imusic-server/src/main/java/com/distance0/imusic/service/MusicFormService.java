package com.distance0.imusic.service;

import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.User;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:42
 * @description:
 */
public interface MusicFormService {
    /**
     * 根据用户id查询收藏歌单
     * @return
     */
    User getMusicFormByUserId();

    /**
     * 根据歌单id查询歌单
     * @param id
     * @return
     */
    MusicForm getMusicFormById(Long id);
}
