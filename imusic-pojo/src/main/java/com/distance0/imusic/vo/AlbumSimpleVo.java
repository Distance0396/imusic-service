package com.distance0.imusic.vo;

import com.distance0.imusic.entity.Music;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/6/9 下午8:00
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSimpleVo implements Serializable {
    private Long id;
    private String name;
    private String singerName;
    private Long singerId;
    @JsonFormat(pattern = "yyyy")
    private LocalDateTime releaseTime;
    private String image;
    private String color;
    private static final long serialVersionUID = 1L;
}
