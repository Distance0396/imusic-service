package com.distance0.imusic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: XiangJing
 * @date: 2024/6/19 上午12:32
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectForm {
    private Long id;
    private Long userId;
    private Long singerId;
    private Long albumId;
    private Long musicFormId;
}
