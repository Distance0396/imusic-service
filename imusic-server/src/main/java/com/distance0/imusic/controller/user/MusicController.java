package com.distance0.imusic.controller.user;

import com.distance0.imusic.result.R;
import com.distance0.imusic.service.MusicService;
import com.distance0.imusic.vo.MusicLyricVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XiangJing
 * @date: 2024/6/20 上午12:03
 * @description:
 */
@Slf4j
@Api(tags = "音乐接口")
@RestController("UserMusicController")
@RequestMapping("/user/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 根据音乐id查询歌词
     * @param id
     * @return
     */
    @GetMapping("/lyric")
    @ApiOperation("根据音乐id查询歌词")
    public R<MusicLyricVo> getLyric(@RequestParam("id") Long id){
        log.info("根据音乐id查询歌词：{}", id);
        MusicLyricVo lyric = musicService.getLyric(id);
        return R.success(lyric);
    }
}
