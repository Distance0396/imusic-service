package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/23 上午8:58
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumPageVo implements Serializable {
    private Long id;
    private String name;
    private String singerName;
    private String image;
    private String description;
    private Integer status;
    private LocalDateTime releaseTime;
    private LocalDateTime createTime;
    private static final long serialVersionUID = 1L;
}
