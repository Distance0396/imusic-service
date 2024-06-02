package com.distance0.imusic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:39
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicForm implements Serializable {
    private Long id;
    private String name;
    private Long userId;
    private String image;
    private Integer count;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Music> musicList;

    private static final long serialVersionUID = 1L;
}
