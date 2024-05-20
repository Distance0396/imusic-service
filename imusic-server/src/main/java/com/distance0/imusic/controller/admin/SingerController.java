package com.distance0.imusic.controller.admin;

import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.dto.SingerSaveDto;
import com.distance0.imusic.result.PageResult;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("返回结果：{}",result);
        return R.success(result);
    }

    /**
     * 添加歌手
     * @param dto
     * @return
     */
    @ApiOperation("添加歌手")
    @PostMapping("/save")
    public R save(SingerSaveDto dto){
        log.info("添加歌手:{}",dto);
        singerService.save(dto);
        return R.success();
    }



//    @PostMapping("/insert")
//    public R insert(@RequestBody){
//        log.info("添加歌手：{}",);
//    }
}
