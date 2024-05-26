package com.distance0.imusic.mapper;

import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:23
 * @description:
 */
@Mapper
public interface SingerMapper {
    /**
     * 添加歌手
     * @param singer
     */
    void insert(Singer singer);

    /**
     * 获取歌手
     * @param singer
     * @return
     */
    Singer getSinger(Singer singer);


    /**
     * 歌手分页查询
     * @param dto
     * @return
     */
    Page<SingerVo> pageQuery(SingerPageDto dto);

    /**
     * 修改歌手
     * @param singer
     */
    void update(Singer singer);
}
