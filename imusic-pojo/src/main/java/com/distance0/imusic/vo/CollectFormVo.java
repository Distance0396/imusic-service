package com.distance0.imusic.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author: XiangJing
 * @date: 2024/6/18 上午11:24
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectFormVo implements Serializable {
    private List<AlbumSimpleVo> albumList;
    private List<SingerSimpleVo> singerList;
    private List<MusicFormSimpleVo> musicFormList;

    private static final long serialVersionUID = 1L;

}
