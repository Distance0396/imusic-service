package com.distance0.imusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.constant.StatusConstant;
import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.dto.UserMiniDto;
import com.distance0.imusic.dto.UserRegisterDto;
import com.distance0.imusic.entity.User;
import com.distance0.imusic.dto.UserLoginDto;
import com.distance0.imusic.exception.*;
import com.distance0.imusic.mapper.UserMapper;
import com.distance0.imusic.properties.WeChatProperties;
import com.distance0.imusic.service.UserService;
import com.distance0.imusic.utils.HttpClientUtil;
import com.distance0.imusic.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:47
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatProperties weChatProperties;


    /**
     * 用户登录
     * @param dto
     * @return
     */
    @Override
    public User login(UserLoginDto dto) {
//        User getUser = userMapper.getUserByAccount(dto.getAccount());
        User getUser = userMapper.getUser(User.builder().account(dto.getAccount()).build());
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
     * 根据授权码向微信请求用户openId
     * @param code
     * @return
     */
    private String getOpenId(String code){
        HashMap<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }

    /**
     * 小程序 用户登录
     * @param dto
     * @return
     */
    @Override
    public User wxLogin(UserMiniDto dto) {
        String openId = getOpenId(dto.getCode());
        if(openId == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("appid","wx04f02e2acebb5a48");
            map.put("secret","50719df90b7807d61451714f211a2af9");
            map.put("js_code",dto.getCode());
            map.put("grant_type","authorization_code");
            String json = HttpClientUtil.doGet(WX_LOGIN, map);

            JSONObject jsonObject = JSON.parseObject(json);
            openId = jsonObject.getString("openid");
            if (openId == null){
                throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
            }
        }

        User user = userMapper.getUserByOpenId(openId);
        if(user != null){
            return user;
        }
        Random rand = new Random();
        User build = User.builder()
                .name("用户"+ rand.nextInt(90000) + 100)
                .openId(openId)
                .createTime(LocalDateTime.now())
                .status(StatusConstant.ENABLE)
                .build();
        userMapper.insert(build);
        return build;
    }

    /**
     * 用户注册
     * @param dto
     * @return
     */
    @Override
    public void register(UserRegisterDto dto) {
//        User user = userMapper.getUserByName(dto.getName());
        User user = userMapper.getUser(User.builder().name(dto.getName()).build());
        if(user != null){
            throw new NameOccupancyException(MessageConstant.NAME_OCCUPANCY);
        }
//        User getUser = userMapper.getUserByAccount(dto.getAccount());
        User getUser = userMapper.getUser(User.builder().account(dto.getAccount()).build());
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
        userMapper.insert(build);
    }

    /**
     * 获取用户自身信息
     * @return
     */
    @Override
    public User getUserInfo() {
        Long contextId = BaseContext.getContextId();
        User user = userMapper.getUser(User.builder().id(contextId).build());
        user.setPassword("你猜我猜猜");
        return user;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserSimpleVo getSimpleUserById(Long id) {
        User user = userMapper.getUser(User.builder().id(id).build());
        UserSimpleVo simpleUserVo = new UserSimpleVo();
        BeanUtils.copyProperties(user,simpleUserVo);
        return simpleUserVo;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public void update(User user) {
        if (userMapper.getUser(user) == null){
            throw new FindNullException(MessageConstant.NOT_FIND_USER);
        }
        userMapper.update(user);
    }

}
