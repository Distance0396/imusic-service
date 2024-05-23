package com.distance0.imusic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/23 下午5:29
 * @description:
 */
@Data
public class AlbumSaveDto {
    private String name;
    private String SingerName;
    private String image;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;
}
