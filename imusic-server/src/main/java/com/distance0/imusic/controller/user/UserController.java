package com.distance0.imusic.controller.user;

import com.distance0.imusic.constant.JwtConstant;
import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.dto.UserRegisterDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.properties.JwtProperties;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.UserService;
import com.distance0.imusic.utils.JwtUtil;
import com.distance0.imusic.vo.SimpleUserVo;
import com.distance0.imusic.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:41
 * @description:
 */
@Slf4j
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public R<User> info() {
        log.info("获取用户信息");
        User user = userService.getUserInfo();
        return R.success(user);
    }

    /**
     * 用户登录
     * @param dto
     * @return
     */
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto dto) {
        log.info("用户登录：{}", dto);
        User user = userService.login(dto);

        // 生成令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.USER_ID, user.getId());

        String jwt = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVo build = UserLoginVo.builder()
                .id(user.getId())
                .name(user.getName())
                .account(user.getAccount())
                .token(jwt)
                .build();
        return R.success(build);
    }

    /**
     * 用户注册
     * @param dto
     * @return
     */
    @ApiModelProperty("用户注册")
    @PostMapping("/register")
    public R register(@RequestBody UserRegisterDto dto) {
        log.info("用户注册：{}", dto);
        userService.register(dto);
        return R.success();
    }

    /**
     * 根据id查询简单用户
     * @param id
     * @return
     */
    @GetMapping
    @ApiOperation("根据id查询简单用户")
    public R<SimpleUserVo> getSimpleUserById(@RequestParam("id") Long id) {
        log.info("根据id查询简单用户:{}",id);
        SimpleUserVo simpleUserVo = userService.getSimpleUserById(id);
        return R.success(simpleUserVo);
    }


}
