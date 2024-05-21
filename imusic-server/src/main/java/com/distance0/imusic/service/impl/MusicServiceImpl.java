package com.distance0.imusic.service.impl;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.MusicService;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:36
 * @description:
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;

    /**
     * 音乐分页
     * @param dto
     * @return
     */
    @Override
    public PageResult page(MusicPageDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        Page<Music> page = musicMapper.pageQuery(dto);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
