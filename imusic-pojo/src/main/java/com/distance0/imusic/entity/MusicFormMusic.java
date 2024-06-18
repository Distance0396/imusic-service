package com.distance0.imusic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/6/11 下午4:06
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicFormMusic implements Serializable {
    private Long id;
    private Long musicFormId;
    private Long musicId;
    private static final long serialVersionUID = 1L;
}
