package com.distance0.imusic.service;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.dto.MusicSaveDto;
import com.distance0.imusic.result.PageResult;

import java.util.List;

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

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    void changeStatus(Integer status, List<Long> id);

    /**
     * 添加歌手
     * @param dto
     * @return
     */
    void save(MusicSaveDto dto);
}
