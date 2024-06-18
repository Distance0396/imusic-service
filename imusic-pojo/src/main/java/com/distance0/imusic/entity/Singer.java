package com.distance0.imusic.entity;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午2:29
 * @description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Singer implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private Integer type;
    private Integer status;
    private String description;
    private LocalDateTime createTime;
    private String image;
    private String language;
    private String color;
    private static final long serialVersionUID = 1L;

}
