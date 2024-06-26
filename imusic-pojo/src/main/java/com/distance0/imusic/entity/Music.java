package com.distance0.imusic.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午3:19
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music implements Serializable {
    private Long id;
    private String name;
    private Long albumId;
    private Long singerId;
    private String albumName;
    private String singerName;
    private String audio;
    private Integer count;
    private Integer sort;
    private String lyric;
    private Integer status;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime releaseTime;
    private static final long serialVersionUID = 1L;
}
