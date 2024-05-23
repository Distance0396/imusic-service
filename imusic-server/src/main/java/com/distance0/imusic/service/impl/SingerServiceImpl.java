package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.constant.TypeConstant;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.FindNullException;
import com.distance0.imusic.exception.SingerNameOccupancyException;
import com.distance0.imusic.exception.StatusException;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.SingerService;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:24
 * @description:
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private AlbumMapper albumMapper;

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
    public void save(SingerSaveDto dto) {
        Singer build = Singer.builder()
                .name(dto.getName())
                .build();
        //先判断数据是否重复
        Singer singer = singerMapper.getSinger(build);

        if (singer == null){
            BeanUtils.copyProperties(dto, build);
            build.setStatus(1);
            build.setCreateTime(LocalDateTime.now());
            singerMapper.insert(build);
        }

        throw new SingerNameOccupancyException(MessageConstant.SINGER_NAME_OCCUPANCY);

    }

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public void changeStatus(Integer status, List<Long> id) {
        int newStatus;
        if (status == StatusConstant.DISABLE){
            newStatus = StatusConstant.DISABLE;
        }else if (status == StatusConstant.ENABLE){
            newStatus = StatusConstant.ENABLE;
        } else {
            throw new StatusException(MessageConstant.STATUS_EXCEPTION);
        }

        for(Long x : id){
            Singer singer = new Singer();
            singer.setId(x);
            singer.setStatus(newStatus);
            singerMapper.updateSinger(singer);
        }

    }

    /**
     * 根据歌手名查询专辑
     * @param name
     * @return
     */
    @Override
    public List<Album> findAlbumBySingerName(String name) {

        Singer build = Singer.builder()
                .name(name)
                .build();
        Singer singer = singerMapper.getSinger(build);
        if (singer == null){
            throw new FindNullException(MessageConstant.FIND_NULL);
        }
        Album album = Album.builder()
                .singerId(singer.getId()).build();

        return albumMapper.getAlbumList(album);
    }


}
