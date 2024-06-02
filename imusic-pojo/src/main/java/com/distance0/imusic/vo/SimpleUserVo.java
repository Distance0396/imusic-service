package com.distance0.imusic.vo;

import com.distance0.imusic.entity.MusicForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午10:01
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserVo implements Serializable {
    private Long id;
    private String name;
    private String account;
    private Integer phone;
    private String avatar;
    private String email;
    private Integer sex;
    private static final long serialVersionUID = 1L;
}
