package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/6/17 上午8:25
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMiniLoginVo implements Serializable {
    private Long id;
    private String openid;
    private String token;
}
