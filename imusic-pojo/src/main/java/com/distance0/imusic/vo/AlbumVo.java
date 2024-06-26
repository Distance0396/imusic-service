package com.distance0.imusic.vo;

import com.distance0.imusic.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/24 上午8:33
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumVo implements Serializable {
    private Long id;
    private String name;
    private String singerName;
    private Long singerId;
    private String image;
    private String color;
    private String description;
    private LocalDateTime releaseTime;
    private List<MusicImageVo> musicList;
    private static final long serialVersionUID = 1L;

}
