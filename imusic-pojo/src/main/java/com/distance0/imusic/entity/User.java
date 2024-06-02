package com.distance0.imusic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:32
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String name;
    private String account;
    private String password;
    private Integer phone;
    private String avatar;
    private String email;
    private Integer sex;
    private Integer status;
    private LocalDateTime createTime;
    private List<MusicForm> musicFormList;
    private static final long serialVersionUID = 1L;
}
