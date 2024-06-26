package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/6/15 下午5:40
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicFormSimpleVo implements Serializable {
    private Long id;
    private String name;
    private String architect;
    private String image;
    private static final long serialVersionUID = 1L;
}
