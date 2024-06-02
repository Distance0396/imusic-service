package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.dto.UserRegisterDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.exception.AccountLockedException;
import com.distance0.imusic.exception.AccountNotFountException;
import com.distance0.imusic.exception.NameOccupancyException;
import com.distance0.imusic.exception.PasswordErrorException;
import com.distance0.imusic.service.UserService;
import com.distance0.imusic.mapper.UserMapper;
import com.distance0.imusic.vo.SimpleUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:47
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param dto
     * @return
     */
    @Override
    public User login(UserLoginDto dto) {
        User getUser = userMapper.getUserByAccount(dto.getAccount());
        // 先判断用户账号是否存在
        if (getUser == null) {
            throw new AccountNotFountException(MessageConstant.ACCOUNT_NOT_FOUNT);
        }
        // 对前端传入的明文密码加密,在与查出的数据对比
        String password = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        if(!password.equals(getUser.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        // 在判断用户是否被锁定
        if(getUser.getStatus() == StatusConstant.DISABLE){
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return getUser;
    }

    /**
     * 用户注册
     * @param dto
     * @return
     */
    @Override
    public void register(UserRegisterDto dto) {
        User user = userMapper.getUserByName(dto.getName());
        if(user != null){
            throw new NameOccupancyException(MessageConstant.NAME_OCCUPANCY);
        }
        User getUser = userMapper.getUserByAccount(dto.getAccount());
        if (getUser != null) {
            throw new AccountNotFountException(MessageConstant.ACCOUNT_OCCUPANCY);
        }

        String password = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());

        User build = User.builder()
                .name(dto.getName())
                .account(dto.getAccount())
                .password(password)
                .status(StatusConstant.ENABLE)
                .sex(3)
                .createTime(LocalDateTime.now())
                .build();
        userMapper.register(build);
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public User getUserInfo() {
        Long contextId = BaseContext.getContextId();
        User build = User.builder()
                .id(contextId)
                .build();
        build = userMapper.getUser(build);
        build.setPassword("你猜我猜猜");
        return build;
    }

    /**
     * 根据id查询简单用户
     * @param id
     * @return
     */
    @Override
    public SimpleUserVo getSimpleUserById(Long id) {
        User user = userMapper.getUser(User.builder().id(id).build());
        SimpleUserVo simpleUserVo = new SimpleUserVo();
        BeanUtils.copyProperties(user,simpleUserVo);
        return simpleUserVo;
    }
}
