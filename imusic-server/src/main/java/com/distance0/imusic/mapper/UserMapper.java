package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:47
 * @description:
 */
@Mapper
public interface UserMapper {
    /**
     * @param user
     * @return
     */
    User getUser(User user);


    /**
     * 根据账号获取用户
     * @param account
     * @return
     */
    @Select("select id, name, account, password, phone, avatar, email, sex, create_time from user where account = #{account}")
    User getUserByAccount(String account);

    /**
     * 根据昵称获取用户
     * @param name
     * @return
     */
    @Select("select id, name, account, password, phone, avatar, email, sex, create_time from user where name = #{name}")
    User getUserByName(String name);

    /**
     * 用户注册
     * @param build
     * @return
     */
    @Insert("insert into user (name, account, password, phone, avatar, status, email, sex, create_time) values" +
            " (#{name},#{account},#{password},#{phone},#{avatar},#{status},#{email},#{sex},#{createTime})")
    void register(User build);
}
