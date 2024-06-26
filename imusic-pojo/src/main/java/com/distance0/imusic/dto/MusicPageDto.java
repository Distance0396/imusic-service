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
    @ApiModelProperty("音乐名字")
    private String name;
    @ApiModelProperty("专辑名")
    private String albumName;
    @ApiModelProperty("歌手名")
    private String singerName;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("页码")
    private int page;
    @ApiModelProperty("每页记录数")
    private int pageSize;
}
