package com.distance0.imusic.service.impl;

import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:36
 * @description:
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;
}
