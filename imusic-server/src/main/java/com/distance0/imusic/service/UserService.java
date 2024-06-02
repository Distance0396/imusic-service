package com.distance0.imusic.service;

import com.distance0.imusic.dto.UserRegisterDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.vo.SimpleUserVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:47
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
    SimpleUserVo getSimpleUserById(Long id);
}
