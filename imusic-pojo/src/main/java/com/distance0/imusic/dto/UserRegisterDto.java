package com.distance0.imusic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午4:59
 * @description:
 */
@Data
public class UserRegisterDto {
    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String CAPTCHA;
}
