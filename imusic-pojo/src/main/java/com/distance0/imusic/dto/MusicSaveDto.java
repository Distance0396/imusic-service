package com.distance0.imusic.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/22 上午11:27
 * @description:
 */
@Data
public class MusicSaveDto {
    private String name;
    private Long albumId;
    private String singerName;
    private String audio;
    private Integer sort;
    private String lyric;
    private LocalDateTime releaseTime;
}
