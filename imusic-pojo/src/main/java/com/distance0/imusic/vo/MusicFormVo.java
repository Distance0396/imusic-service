package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/6 下午8:31
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicFormVo implements Serializable {
    private Long id;
    private String name;
    private String architect;
    private String image;
    private String color;
    private Integer count;
    private String description;
    private List<MusicImageVo> musicList;
    private static final long serialVersionUID = 1L;
}
