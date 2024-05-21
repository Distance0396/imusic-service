package com.distance0.imusic.service;

import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.result.PageResult;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:24
 * @description:
 */
public interface SingerService {

    /**
     * 歌手分页查询
     * @param dto
     * @return
     */
    PageResult page(SingerPageDto dto);

    /**
     * 添加歌手
     * @param dto
     * @return
     */
    void save(SingerSaveDto dto);

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    void changeStatus(Integer status, List<Long> id);
}
