package com.distance0.imusic.service;

import com.distance0.imusic.entity.MusicFormMusic;
import com.distance0.imusic.vo.MusicFormVo;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:42
 * @description:
 */
public interface MusicFormService {
    /**
     * 根据用户id查询收藏歌单
     * @return
     */
//    List<MusicFormVo> getMusicFormByUserId();

    /**
     * 根据歌单id查询歌单
     * @param id
     * @return
     */
    MusicFormVo getMusicFormById(Long id);

    /**
     * 新增歌单
     * @return
     */
    void addMusicForm();

    /**
     * 添加歌曲至歌单
     * @param musicFormMusic
     * @return
     */
    void appendMusicForm(MusicFormMusic musicFormMusic);

    /**
     * 删除歌单歌曲
     * @param musicFormMusic
     * @return
     */
    void deleteMusic(MusicFormMusic musicFormMusic);
}
