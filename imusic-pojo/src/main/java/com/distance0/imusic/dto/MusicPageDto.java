package com.distance0.imusic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: XiangJing
 * @date: 2024/5/22 上午12:44
 * @description:
 */
@Data
public class MusicPageDto {
    @ApiModelProperty("歌手")
    private String name;
    @ApiModelProperty("专辑")
    private String album;
    @ApiModelProperty("页码")
    private int page;
    @ApiModelProperty("每页记录数")
    private int pageSize;
}
