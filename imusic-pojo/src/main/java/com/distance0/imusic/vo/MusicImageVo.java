package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/6/3 上午8:28
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicImageVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long albumId;
    private Long singerId;
    private String albumName;
    private String singerName;
    private String audio;
    private Integer count;
    private String image;
}
