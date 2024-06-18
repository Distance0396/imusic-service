package com.distance0.imusic.vo;

import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.entity.Singer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/5 下午9:43
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchVo implements Serializable {
    private List<MusicImageVo> musicList;
    private List<Album> albumList;
    private List<Singer> singerList;
    private static final long serialVersionUID = 1L;
}
