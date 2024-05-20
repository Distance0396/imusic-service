package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: XiangJing
 * @date: 2024/5/20 下午7:03
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingerVo implements Serializable {
    private Long id;
    private String name;
    private String avatar;
    private Integer type;
    private Integer status;
    private String description;
    private LocalDateTime createTime;
    private static final long serialVersionUID = 1L;
}
