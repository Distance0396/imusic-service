package com.distance0.imusic.service.impl;

import com.alibaba.druid.sql.dialect.hive.ast.HiveMultiInsertStatement;
import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.dto.AlbumPageDto;
import com.distance0.imusic.dto.AlbumDto;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.FindNullException;
import com.distance0.imusic.exception.StatusException;
import com.distance0.imusic.exception.UnknownErrorException;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.AlbumService;
import com.distance0.imusic.utils.ImagesUtil;
import com.distance0.imusic.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    @Autowired
    private MusicMapper musicMapper;

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
            Singer build = Singer
                    .builder()
                    .name(dto.getSingerName())
                    .build();
            Singer singer = singerMapper.getSinger(build);
            if (singer == null){
                throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
            }
            album.setSingerId(singer.getId());
        }

        Page<Album> albums = albumMapper.pageQuery(album);

        List<AlbumPageVo> collect = albums.stream().map(a -> {
            String singerName = singerMapper.getSingerName(a.getSingerId());
            AlbumPageVo build = AlbumPageVo.builder().singerName(singerName).build();
            BeanUtils.copyProperties(a, build);
            return build;
        }).collect(Collectors.toList());

        return new PageResult(albums.getTotal(), collect);
    }

    /**
     * 添加专辑
     * @param dto
     * @return
     */
    @Override
    public void save(AlbumDto dto) {
        // 根据歌手名查找，没有抛出异常并终止方法运行
        Singer build = Singer.builder()
                .name(dto.getSingerName()).build();
        Singer singer = singerMapper.getSinger(build);
        // 查找到歌手就添加数据
        if (singer == null){
            throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
        }

        try {
            Album album = Album.builder()
                    .singerId(singer.getId())
                    .name(dto.getName())
                    .status(StatusConstant.ENABLE)
                    .createTime(LocalDateTime.now())
                    .image(dto.getImage())
                    .color(ImagesUtil.readImages(dto.getImage()))
                    .releaseTime(dto.getReleaseTime())
                    .description(dto.getDescription())
                    .build();
            albumMapper.insert(album);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public void changeStatus(Integer status, List<Long> id) {

        if (!status.equals(StatusConstant.DISABLE) && !status.equals(StatusConstant.ENABLE)){
            throw new StatusException(MessageConstant.STATUS_EXCEPTION);
        }

        for(Long x : id){
            Album album = new Album();
            album.setId(x);
            album.setStatus(status);
            albumMapper.update(album);
        }
    }

    /**
     * 根据id查询专辑 后台
     * @param id
     * @return
     */
    @Override
    public AlbumVo findById(Long id) {
        // 根据id查询专辑
        Album album = Album.builder().id(id).build();
        Album albumByAlbum = albumMapper.select(album);

        // 为空就抛出异常
        if (albumByAlbum != null){
            // 根据歌手id查询歌手名字
            Singer singer = Singer.builder().id(albumByAlbum.getSingerId()).build();
            Singer singer1 = singerMapper.getSinger(singer);
            // 查询专辑中的音乐
            Music musicBuilder = Music
                    .builder()
                    .albumId(albumByAlbum.getId())
                    .build();
            List<Music> musicList = musicMapper.getMusicList(musicBuilder);

            List<MusicImageVo> musicImageVos = musicList.stream().map(x -> {
                MusicImageVo music = new MusicImageVo();
                BeanUtils.copyProperties(x, music);
                return music;
            }).collect(Collectors.toList());

            // 赋值给返回对象
            AlbumVo build = AlbumVo
                    .builder()
                    .singerName(singer1.getName())
                    .musicList(musicImageVos)
                    .build();
            BeanUtils.copyProperties(albumByAlbum, build);

            return build;
        }
        throw new FindNullException(MessageConstant.FIND_NULL);
    }

    /**
     * 修改专辑
     * @param dto
     * @return
     */
    @Override
    public void update(AlbumDto dto) {
        String image = dto.getImage();
        /*
            专辑是否存在
         */
        Album album = albumMapper.select(Album.builder().id(dto.getId()).build());
        if (album == null){
            throw new UnknownErrorException(MessageConstant.UNKNOWN_ERROR);
        }

        /*
            异步读取颜色后添加
         */
        CompletableFuture.runAsync(() -> {
            try {
                Album build = Album.builder().color(ImagesUtil.readImages(image)).build();
                BeanUtils.copyProperties(dto, build);
                albumMapper.update(build);
            } catch (IOException e) {
                throw new RuntimeException(MessageConstant.COLOR_READING_ERROR);
            }
        });

        /*
            修改专辑中歌曲的image
         */
        List<Music> musicList = musicMapper.getMusicList(Music.builder().albumId(dto.getId()).build());
        musicList.forEach(x -> x.setImage(image));
        musicMapper.updateMusicList(musicList);

    }

    /**
     * 获取随机专辑
     * @return
     */
    @Override
    public List<AlbumSimpleVo> getRandomAlbum() {
        List<AlbumVo> randomAlbum = albumMapper.getRandomAlbum();
        return randomAlbum.stream().map(x -> {
            AlbumSimpleVo albumSimpleVo = new AlbumSimpleVo();
            BeanUtils.copyProperties(x, albumSimpleVo);
            Singer singer = singerMapper.getSinger(Singer.builder().id(x.getSingerId()).build());
            albumSimpleVo.setSingerName(singer.getName());
            return albumSimpleVo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据歌手id查询专辑
     * @param id
     * @return
     */
    @Override
    public List<AlbumSimpleVo> getAlbumBySingerId(Long id) {
        List<Album> albumList = albumMapper.getAlbumList(Album.builder().singerId(id).status(1).build());

        return albumList.stream().map(x -> {
            AlbumSimpleVo simpleVo = new AlbumSimpleVo();
            BeanUtils.copyProperties(x, simpleVo);

            String singerName = singerMapper.getSingerName(x.getSingerId());
            simpleVo.setSingerName(singerName);
            return simpleVo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据专辑id查询信息
     * @param id
     * @return
     */
    @Override
    public AlbumVo getAlbumById(Long id) {
        Album album = albumMapper.select(Album.builder().id(id).build());
        List<Music> musicList = musicMapper.getMusicList(Music.builder().albumId(album.getId()).build());

        List<MusicImageVo> collect = musicList.stream().map(music -> {
            MusicImageVo musicImageVo = new MusicImageVo();
            BeanUtils.copyProperties(music, musicImageVo);
            musicImageVo.setImage(album.getImage());
            return musicImageVo;
        }).collect(Collectors.toList());

        Singer singer = singerMapper.getSinger(Singer.builder().id(album.getSingerId()).build());
        AlbumVo build = AlbumVo.builder().singerName(singer.getName()).musicList(collect).build();
        BeanUtils.copyProperties(album, build);
        return build;
    }
}
