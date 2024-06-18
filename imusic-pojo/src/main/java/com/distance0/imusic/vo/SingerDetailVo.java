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
 * @date: 2024/5/29 下午4:43
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingerDetailVo implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private Integer type;
    private String description;
    private String color;
//    private LocalDateTime createTime;
    private String image;
    private String language;
    private List<MusicImageVo> musicList;
    private List<AlbumSimpleVo> albumList;
    private static final long serialVersionUID = 1L;
}
