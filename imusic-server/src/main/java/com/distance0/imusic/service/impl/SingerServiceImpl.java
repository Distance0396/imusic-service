package com.distance0.imusic.service.impl;

import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:24
 * @description:
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;
}
