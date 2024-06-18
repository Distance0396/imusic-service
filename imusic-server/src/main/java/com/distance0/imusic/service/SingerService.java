package com.distance0.imusic.service;

import com.distance0.imusic.dto.SingerDto;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.vo.SingerDetailVo;
import com.distance0.imusic.vo.SingerSimpleVo;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/15 下午5:16
 * @description:
 */
public interface SingerService {


    /**
     * @author: XiangJing
     * @date: 2024/5/20 下午1:24
     * @description:
     */


    /**
     * 歌手分页查询
     *
     * @param dto
     * @return
     */
    PageResult page(SingerPageDto dto);

    /**
     * 添加歌手
     *
     * @param dto
     * @return
     */
    void save(SingerSaveDto dto);

    /**
     * 修改状态
     *
     * @param status
     * @param id
     * @return
     */
    void changeStatus(Integer status, List<Long> id);

    /**
     * 根据歌手名查询专辑
     *
     * @param name
     * @return
     */
    List<Album> findAlbumBySingerName(String name);

    /**
     * 根据id查询歌手详情
     *
     * @param id
     * @return
     */
    Singer findSingerById(Long id);

    /**
     * 修改歌手信息
     *
     * @param dto
     * @return
     */
    void update(SingerDto dto);

    /**
     * 获取歌手数组
     *
     * @return
     */
    List<SingerSimpleVo> getSingerList();

    /**
     * 随机获取歌手
     *
     * @return
     */
    List<SingerSimpleVo> getRandomSinger();


    /**
     * 根据id查找歌手详情(音乐列表)
     *
     * @param id
     * @return
     */
    SingerDetailVo findSingerDetailById(Long id);
}


