package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.dto.SingerDto;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.exception.FindNullException;
import com.distance0.imusic.exception.SingerNameOccupancyException;
import com.distance0.imusic.exception.StatusException;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.service.SingerService;
import com.distance0.imusic.vo.MusicDetailVo;
import com.distance0.imusic.vo.SingerDetailVo;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private MusicMapper musicMapper;

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
            singerMapper.update(singer);
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

    /**
     * 根据id查询歌手详情
     *
     * @param id
     * @return
     */
    @Override
    public Singer findSingerById(Long id) {
        Singer build = Singer.builder().id(id).build();
        Singer singer = singerMapper.getSinger(build);

        if (singer != null){
            return singer;
        }

        throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
    }

    /**
     * 修改歌手信息
     * @param dto
     * @return
     */
    @Override
    public void update(SingerDto dto) {
        Singer build = Singer.builder().name(dto.getName()).build();
        Singer singer1 = singerMapper.getSinger(build);

        if (singer1 != null){
            Singer singer = new Singer();
            BeanUtils.copyProperties(dto, singer);
            singerMapper.update(singer);
            return;
        }

        throw new SingerNameOccupancyException(MessageConstant.NAME_OCCUPANCY);
    }


    /**
     * 获取歌手数组
     * @return
     */
    @Override
    public List<Singer> getSingerList() {
        return singerMapper.selectAll();
    }

    /**
     * 随机获取歌手
     * @return
     */
    @Override
    public List<Singer> getRandomSinger() {
        return singerMapper.getRandomSinger();
    }

    /**
     * 根据id查找歌手详情(音乐列表)
     * @param id
     * @return
     */
    @Override
    public SingerDetailVo findSingerDetailById(Long id) {
        Singer singer = singerMapper.getSinger(Singer.builder().id(id).build());

        if (singer != null){
            List<Music> musicList = musicMapper.getMusicList(Music.builder().singerId(singer.getId()).build());
            System.out.println("musicList:" + musicList);

            List<MusicDetailVo> collect = musicList.stream().map(music -> {

                Album albumByAlbum = null;
               if (music.getAlbumId() != null){
                   albumByAlbum = albumMapper.getAlbumByAlbum(Album.builder().id(music.getAlbumId()).build());
               }

                MusicDetailVo musicDetailVo = new MusicDetailVo();
                BeanUtils.copyProperties(music, musicDetailVo);
                if (albumByAlbum != null && albumByAlbum.getImage() != null) {
                    musicDetailVo.setAlbumImage(albumByAlbum.getImage());
                }

                return musicDetailVo;
            }).collect(Collectors.toList());

            SingerDetailVo singerDetailVo = SingerDetailVo.builder()
                    .musicList(collect)
                    .build();
            BeanUtils.copyProperties(singer, singerDetailVo);

            return singerDetailVo;
        }

        throw new FindNullException(MessageConstant.FIND_SINGER_NULL);
    }


}
