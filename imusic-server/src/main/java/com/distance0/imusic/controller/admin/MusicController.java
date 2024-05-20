package com.distance0.imusic.controller.admin;

import com.distance0.imusic.service.MusicService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:37
 * @description:
 */
@Slf4j
@Api(tags = "音乐接口")
@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;
}
