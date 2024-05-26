package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/26 下午2:26
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicVo implements Serializable {
    private Long id;
    private String name;
    private Long albumId;
    private String albumName;
    private String singerName;
    private String audio;
    private Integer count;
    private Integer sort;
    private String lyric;
    private LocalDateTime releaseTime;
    private static final long serialVersionUID = 1L;
}
