package com.distance0.imusic.service;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.result.PageResult;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:36
 * @description:
 */
public interface MusicService {
    /**
     * 音乐分页
     * @param dto
     * @return
     */
    PageResult page(MusicPageDto dto);
}
