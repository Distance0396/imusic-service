package com.distance0.imusic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午6:39
 * @description:
 */
@Data
public class SingerPageDto {
    @ApiModelProperty("歌手名")
    private String name;
    @ApiModelProperty("类型")
    private int type;
    @ApiModelProperty("状态")
    private int status;
    @ApiModelProperty("页码")
    private int page;
    @ApiModelProperty("每页记录数")
    private int pageSize;
}
