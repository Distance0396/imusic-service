package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.TypeConstant;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.SingerNameOccupancyException;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.SingerService;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:24
 * @description:
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 歌手分页查询
     * @param dto
     * @return
     */
    @Override
    public PageResult page(SingerPageDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        Page<SingerVo> page = singerMapper.pageQuery(dto);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 添加歌手
     * @param dto
     * @return
     */
    @Override
    public void save(@RequestBody SingerSaveDto dto) {
        Singer build = Singer.builder()
                .name(dto.getName())
                .build();
        //先判断数据是否重复
        Singer singer = singerMapper.getSinger(build);
        if (singer != null){
            BeanUtils.copyProperties(dto, build);
            singerMapper.insert(build);
        }

        throw new SingerNameOccupancyException(MessageConstant.SINGER_NAME_OCCUPANCY);
    }
}
