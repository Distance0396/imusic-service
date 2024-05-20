package com.distance0.imusic.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午3:19
 * @description:
 */
public class Music implements Serializable {
    private Long id;
    private String name;
    private Long albumId;
    private Long singerId;
    private String audio;
    private Integer count;
    private Integer sort;
    private String lyric;
    private LocalDateTime createTime;
    private static final long serialVersionUID = 1L;
}
