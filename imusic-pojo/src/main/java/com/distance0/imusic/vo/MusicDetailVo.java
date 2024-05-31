package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/29 下午5:31
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicDetailVo implements Serializable {
    private Long id;
    private String name;
    private Long albumId;
    private String albumName;
    private String singerName;
    private String audio;
    private Integer count;
    private Integer sort;
    private String lyric;
    private String albumImage;
    private LocalDateTime releaseTime;
    private static final long serialVersionUID = 1L;
}
