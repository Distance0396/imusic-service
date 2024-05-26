package com.distance0.imusic.dto;

import com.distance0.imusic.entity.Music;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/23 下午5:29
 * @description:
 */
@Data
public class AlbumDto {
    private Long id;
    private String name;
    private String SingerName;
    private String image;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime releaseTime;
}
