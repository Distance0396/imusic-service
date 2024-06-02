package com.distance0.imusic.service.impl;

import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.mapper.MusicFormMapper;
import com.distance0.imusic.service.MusicFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:42
 * @description:
 */
@Service
public class MusicFormServiceImpl implements MusicFormService {

    @Autowired
    private MusicFormMapper musicFormMapper;

    /**
     * 根据用户id查询收藏歌单
     * @return
     */
    @Override
    public User getMusicFormByUserId() {
        User user = musicFormMapper.getMusicFormByUser(BaseContext.getContextId());
        System.out.println(user);
        return user;
    }

    /**
     * 根据歌单id查询歌单
     * @param id
     * @return
     */
    @Override
    public MusicForm getMusicFormById(Long id) {
        return musicFormMapper.getMusicFormById(id);
    }
}
