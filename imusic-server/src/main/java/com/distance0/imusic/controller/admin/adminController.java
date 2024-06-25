package com.distance0.imusic.controller.admin;

import com.distance0.imusic.constant.JwtConstant;
import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.result.R;
import com.distance0.imusic.utils.JwtUtil;
import com.distance0.imusic.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: XiangJing
 * @date: 2024/5/20 上午12:52
 * @description:
 */
@Slf4j
@Api(tags = "admin接口")
@RestController
@RequestMapping("/admin")
public class adminController {


    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public R login() {
        log.info("admin登陆");
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return R.success(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public R info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return R.success(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public R logout(){
        return R.success();
    }

}
