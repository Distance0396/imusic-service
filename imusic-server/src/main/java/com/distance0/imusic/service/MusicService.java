package com.distance0.imusic.service;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.dto.MusicDto;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.vo.MusicVo;

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
    void save(MusicDto dto);

    /**
     * 修改音乐
     * @param dto
     * @return
     */
    void update(MusicDto dto);

    /**
     * 根据id查询歌曲
     * @param id
     * @return
     */
    MusicVo findMusicById(Long id);
}
