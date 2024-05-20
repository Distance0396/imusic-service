package com.distance0.imusic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:38
 * @description:
 */
@Data
public class UserLoginDto implements Serializable {

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;
}
