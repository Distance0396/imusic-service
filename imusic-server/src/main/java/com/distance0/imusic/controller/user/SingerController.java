package com.distance0.imusic.controller.user;

import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.SingerService;
import com.distance0.imusic.vo.SingerDetailVo;
import com.distance0.imusic.vo.SingerSimpleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/27 上午8:54
 * @description:
 */
@Slf4j
@Api(tags = "歌手接口")
@RestController("UserSingerController")
@RequestMapping("/user/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 获取歌手数组
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获取所有歌手")
    public R<List<SingerSimpleVo>> getSingerList(){
        log.info("获取所有歌手");
        List<SingerSimpleVo> singerList = singerService.getSingerList();
        return R.success(singerList);
    }

    /**
     * 根据id查找歌手详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查找歌手详情")
    public R<SingerDetailVo> getSinger(@PathVariable Long id){
        log.info("根据id查找歌手详情");
        SingerDetailVo singer = singerService.findSingerDetailById(id);
        return R.success(singer);
    }

    /**
     * 随机获取歌手
     * @return
     */
    @ApiOperation("随机获取歌手")
    @GetMapping("/random")
    public R<List<SingerSimpleVo>> getRandomSinger(){
        log.info("随机获取歌手");
        List<SingerSimpleVo> singerList = singerService.getRandomSinger();
        return R.success(singerList);
    }
}
