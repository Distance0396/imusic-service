package com.distance0.imusic.controller.admin;

import com.distance0.imusic.service.SingerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
