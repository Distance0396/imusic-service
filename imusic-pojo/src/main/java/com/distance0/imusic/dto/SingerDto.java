package com.distance0.imusic.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/26 下午7:42
 * @description:
 */
@Data
public class SingerDto implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private Integer type;
    private Integer status;
    private String description;
    private static final long serialVersionUID = 1L;
}
