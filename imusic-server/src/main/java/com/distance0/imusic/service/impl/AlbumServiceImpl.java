package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.dto.AlbumPageDto;
import com.distance0.imusic.dto.AlbumSaveDto;
import com.distance0.imusic.dto.MusicSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.FindNullException;
import com.distance0.imusic.exception.NameOccupancyException;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.AlbumService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:41
 * @description:
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 专辑分页
     * @param dto
     * @return
     */
    @Override
    public PageResult page(AlbumPageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        // 使用歌手名称查出id

        Album album = Album.builder()
                .name(dto.getName())
                .status(dto.getStatus())
                .build();

        if (!dto.getSingerName().isEmpty()){
            Singer build = Singer.builder()
                    .name(dto.getSingerName()).build();
            Singer singer = singerMapper.getSinger(build);
            if (singer == null){
                throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
            }
            album.setSingerId(singer.getId());
        }

        Page<Album> albums = albumMapper.pageQuery(album);

        return new PageResult(albums.getTotal(), albums.getResult());
    }

    /**
     * 添加专辑
     * @param dto
     * @return
     */
    @Override
    public void save(AlbumSaveDto dto) {
        // 根据歌手名查找，没有抛出异常并终止方法运行
        Singer build = Singer.builder()
                .name(dto.getSingerName()).build();
        Singer singer = singerMapper.getSinger(build);
        // 查找到歌手就添加数据
        if (singer != null){
            Album album = Album.builder()
                    .singerId(singer.getId())
                    .name(dto.getName())
                    .status(StatusConstant.ENABLE)
                    .createTime(LocalDateTime.now())
                    .image(dto.getImage())
                    .releaseTime(dto.getReleaseTime())
                    .description(dto.getDescription())
                    .build();
            albumMapper.insert(album);
            return;
        }

        throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
    }

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public void changeStatus(Integer status, List<Long> id) {

    }
}
