package com.distance0.imusic.service;

import com.distance0.imusic.dto.AlbumPageDto;
import com.distance0.imusic.dto.AlbumSaveDto;
import com.distance0.imusic.dto.MusicSaveDto;
import com.distance0.imusic.result.PageResult;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:41
 * @description:
 */
public interface AlbumService {

    /**
     * 专辑分页
     * @param dto
     * @return
     */
    PageResult page(AlbumPageDto dto);

    /**
     * 添加专辑
     * @param dto
     * @return
     */
    void save(AlbumSaveDto dto);

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    void changeStatus(Integer status, List<Long> id);
}
