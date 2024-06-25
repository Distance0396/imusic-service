package com.distance0.imusic.service;

import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.dto.UserMiniDto;
import com.distance0.imusic.dto.UserRegisterDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.vo.CollectFormVo;
import com.distance0.imusic.vo.UserSimpleVo;

/**
 * @author: XiangJing
 * @date: 2024/6/15 下午5:20
 * @description:
 */
public interface UserService {
    /**
     * 用户登录
     * @param dto
     * @return
     */
    User login(UserLoginDto dto);

    /**
     * 小程序 用户登录
     * @param dto
     * @return
     */
    User wxLogin(UserMiniDto dto);


    /**
     * 用户注册
     * @param dto
     * @return
     */
    void register(UserRegisterDto dto);

    /**
     * 获取用户信息
     * @return
     */
    User getUserInfo();


    /**
     * 根据id查询简单用户
     * @param id
     * @return
     */
    UserSimpleVo getSimpleUserById(Long id);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    void update(User user);
}
