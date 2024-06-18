package com.distance0.imusic.vo;

import com.distance0.imusic.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private String image;
    private String color;
    private static final long serialVersionUID = 1L;
}
