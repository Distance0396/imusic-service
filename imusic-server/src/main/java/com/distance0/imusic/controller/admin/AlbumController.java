package com.distance0.imusic.controller.admin;

import com.distance0.imusic.dto.AlbumPageDto;
import com.distance0.imusic.dto.AlbumSaveDto;
import com.distance0.imusic.dto.MusicSaveDto;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:42
 * @description:
 */
@Slf4j
@Api(tags = "专辑接口")
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    /**
     * 专辑分页
     * @param dto
     * @return
     */
    @GetMapping("/page")
    public R<PageResult> page(AlbumPageDto dto){
        log.info("专辑分页：{}",dto);
        PageResult result = albumService.page(dto);
        return R.success(result);
    }


    /**
     * 添加专辑
     * @param dto
     * @return
     */
    @ApiOperation("添加专辑")
    @PostMapping("/save")
    public R save(@RequestBody AlbumSaveDto dto){
        log.info("添加音乐:{}",dto);
        albumService.save(dto);
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
        albumService.changeStatus(status, id);
        return R.success();
    }
}
