package com.distance0.imusic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午3:09
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {
    private Long id;
    private String name;
    private Long singerId;
    private String image;
    private String description;
    private LocalDateTime releaseTime;
    private static final long serialVersionUID = 1L;
}
