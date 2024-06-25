package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/6/19 下午11:43
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicLyricVo implements Serializable {
    private Long id;
    private String name;
    private String albumName;
    private String singerName;
    private String lyric;
    private String image;
    private static final long serialVersionUID = 1L;
}
