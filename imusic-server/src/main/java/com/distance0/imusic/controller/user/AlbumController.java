package com.distance0.imusic.controller.user;

import com.distance0.imusic.entity.Album;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.AlbumService;
import com.distance0.imusic.vo.AlbumSimpleVo;
import com.distance0.imusic.vo.AlbumVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/28 下午10:13
 * @description:
 */
@Slf4j
@Api(tags = "专辑接口")
@RestController("UserAlbumController")
@RequestMapping("/user/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 获取随机专辑
     * @return
     */
    @GetMapping("/random")
    @ApiOperation("获取随机专辑")
    public R<List<AlbumSimpleVo>> getRandomAlbum() {
        log.info("获取随机专辑");
        List<AlbumSimpleVo> albums = albumService.getRandomAlbum();
        return R.success(albums);
    }

    /**
     * 根据歌手id查询专辑
     * @param id
     * @return
     */
    @GetMapping
    @ApiOperation("根据歌手id查询专辑")
    public R<List<AlbumSimpleVo>> getAlbumBySingerId(@RequestParam("singerId") Long id ){
        log.info("根据歌手id查询专辑:{}", id);
        List<AlbumSimpleVo> albumVoList = albumService.getAlbumBySingerId(id);
        return R.success(albumVoList);
    }

    /**
     * 根据专辑id查询信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据专辑id查询信息")
    public R<AlbumVo> getAlbumById(@PathVariable Long id){
        log.info("根据专辑id查询信息:{}", id);
        AlbumVo albumVo = albumService.getAlbumById(id);
        return R.success(albumVo);
    }
}
