package com.distance0.imusic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: XiangJing
 * @date: 2024/5/23 上午9:28
 * @description:
 */
@Data
public class AlbumPageDto {
    @ApiModelProperty("专辑名字")
    private String name;
    @ApiModelProperty("歌手名称")
    private String singerName;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("页码")
    private int page;
    @ApiModelProperty("每页记录数")
    private int pageSize;
}
