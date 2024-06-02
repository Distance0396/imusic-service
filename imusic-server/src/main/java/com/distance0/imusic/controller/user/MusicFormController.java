package com.distance0.imusic.controller.user;

import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.MusicFormService;
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
 * @date: 2024/6/2 下午3:41
 * @description:
 */
@Slf4j
@Api(tags = "歌单接口")
@RestController
@RequestMapping("/user/music-form")
public class MusicFormController {
    @Autowired
    private MusicFormService musicFormService;

    /**
     * 根据用户id查询收藏歌单
     * @return
     */
    @ApiOperation("根据用户id查询收藏歌单")
    @GetMapping("/user")
    public R<User> getMusicFormByUserId(){
        log.info("根据用户id查询收藏歌单: {}", BaseContext.getContextId());
        User user = musicFormService.getMusicFormByUserId();
        return R.success(user);
    }

    /**
     * 根据歌单id查询歌单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据歌单id查询歌单")
    public R<MusicForm> getMusicFormById(@PathVariable Long id){
        log.info("根据歌单id查询歌单:{}",id);
        MusicForm musicForm = musicFormService.getMusicFormById(id);
        return R.success(musicForm);
    }
}
