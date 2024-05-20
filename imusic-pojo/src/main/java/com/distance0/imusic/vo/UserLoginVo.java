package com.distance0.imusic.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午4:50
 * @description:
 */
@Data
@Builder
public class UserLoginVo {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("jwt令牌")
    private String token;
}
