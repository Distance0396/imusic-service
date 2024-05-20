package com.distance0.imusic.service.impl;

import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:41
 * @description:
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
}
