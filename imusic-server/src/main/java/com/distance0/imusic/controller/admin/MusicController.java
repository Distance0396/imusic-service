package com.distance0.imusic.controller.admin;

import com.distance0.imusic.dto.MusicPageDto;
import com.distance0.imusic.dto.MusicDto;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.MusicService;
import com.distance0.imusic.vo.MusicLyricVo;
import com.distance0.imusic.vo.MusicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:37
 * @description:
 */
@Slf4j
@Api(tags = "音乐接口")
@RestController
@RequestMapping("/admin/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 音乐分页
     * @param dto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("音乐分页")
    public R<PageResult> page(MusicPageDto dto){
        log.info("音乐分页查询：{}",dto);
        PageResult result = musicService.page(dto);
        return R.success(result);
    }

    /**
     * 添加音乐
     * @param dto
     * @return
     */
    @ApiOperation("添加音乐")
    @PostMapping("/save")
    public R save(@RequestBody MusicDto dto){
        log.info("添加音乐:{}",dto);
        musicService.save(dto);
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
        musicService.changeStatus(status, id);
        return R.success();
    }

    /**
     * 根据id查询歌曲
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询歌曲")
    public R<MusicVo> findMusicById(@PathVariable Long id) {
        log.info("根据id查询歌曲:{}",id);
        MusicVo music = musicService.findMusicById(id);
        log.info(String.valueOf(music));
        return R.success(music);
    }

    /**
     * 修改音乐
     * @param dto
     * @return
     */
    @ApiOperation("修改音乐")
    @PutMapping("/")
    public R update(@RequestBody MusicDto dto){
        log.info("修改音乐：{}", dto);
        musicService.update(dto);
        return R.success();
    }
}
