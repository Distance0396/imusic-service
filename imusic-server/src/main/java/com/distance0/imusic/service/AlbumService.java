package com.distance0.imusic.service;

import com.distance0.imusic.dto.AlbumPageDto;
import com.distance0.imusic.dto.AlbumDto;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.vo.AlbumVo;

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
    void save(AlbumDto dto);

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    void changeStatus(Integer status, List<Long> id);

    /**
     * 根据id查询专辑
     * @param id
     * @return
     */
    AlbumVo findById(Long id);

    /**
     * 修改专辑
     * @param dto
     * @return
     */
    void update(AlbumDto dto);
}
