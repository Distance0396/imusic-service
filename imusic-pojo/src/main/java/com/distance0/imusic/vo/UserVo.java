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
 * @date: 2024/6/6 下午8:30
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private Long id;
    private String name;
    private List<MusicFormVo> musicFormList;
    private static final long serialVersionUID = 1L;
}
