package com.distance0.imusic.controller.admin;

import com.distance0.imusic.dto.SingerDto;
import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:25
 * @description:
 */
@Slf4j
@Api(tags = "歌手接口")
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 歌手分页查询
     * @param dto
     * @return
     */
    @ApiOperation("歌手分页查询")
    @GetMapping("/page")
    public R<PageResult> page(SingerPageDto dto){
        log.info("歌手分页查询：{}",dto);
        PageResult result = singerService.page(dto);
        return R.success(result);
    }

    /**
     * 添加歌手
     * @param dto
     * @return
     */
    @ApiOperation("添加歌手")
    @PostMapping("/save")
    public R save(@RequestBody SingerSaveDto dto){
        log.info("添加歌手:{}",dto);
        singerService.save(dto);
        return R.success();
    }

    /**
     * 修改状态
     * @param status
     * @param id
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("修改状态")
    public R changeStatus(@PathVariable Integer status, @RequestParam List<Long> id) {
        log.info("修改歌手状态：{},{}", status, id);
        singerService.changeStatus(status, id);
        return R.success();
    }

    /**
     * 根据歌手名查询专辑
     * @param name
     * @return
     */
    @ApiOperation("根据歌手名查询专辑")
    @GetMapping("/album")
    public R findAlbumBySingerName(@RequestParam String name) {
        log.info("根据歌手名查询专辑：{}", name);
        List<Album> albumList = singerService.findAlbumBySingerName(name);
        return R.success(albumList);
    }

    /**
     * 根据id查询歌手详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询歌手详情")
    public R<Singer> findMusicById(@PathVariable Long id){
        log.info("根据id查询歌手详情：{}", id);
        Singer singer = singerService.findMusicById(id);
        return R.success(singer);
    }

    /**
     * 修改歌手信息
     * @param dto
     * @return
     */
    @PutMapping("/")
    @ApiOperation("修改歌手信息")
    public R update(@RequestBody SingerDto dto) {
        log.info("修改歌手信息：{}", dto);
        singerService.update(dto);
        return R.success();
    }
}
