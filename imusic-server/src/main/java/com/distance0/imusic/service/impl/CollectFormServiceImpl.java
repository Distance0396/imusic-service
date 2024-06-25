package com.distance0.imusic.service.impl;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.entity.CollectForm;
import com.distance0.imusic.exception.AlreadyExistException;
import com.distance0.imusic.mapper.CollectFormMapper;
import com.distance0.imusic.service.CollectFormService;
import com.distance0.imusic.vo.AlbumSimpleVo;
import com.distance0.imusic.vo.CollectFormVo;
import com.distance0.imusic.vo.MusicFormSimpleVo;
import com.distance0.imusic.vo.SingerSimpleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: XiangJing
 * @date: 2024/6/19 上午12:34
 * @description:
 */
@Service
public class CollectFormServiceImpl implements CollectFormService {

    @Autowired
    private CollectFormMapper collectFormMapper;

    /**
     * 收藏
     * @param collectForm
     */
    @Override
    public void collect(CollectForm collectForm) {
        CollectForm res = collectFormMapper.selectCollectForm(collectForm);
        if (res != null){
            throw new AlreadyExistException(MessageConstant.ALREADY_EXIST);
        }
        collectFormMapper.addCollectForm(collectForm);
    }

    /**
     * 取消收藏
     * @param collectForm
     * @return
     */
    @Override
    public void cancel(CollectForm collectForm) {
        CollectForm res = collectFormMapper.selectCollectForm(collectForm);
        if (res == null){
            throw new AlreadyExistException(MessageConstant.FIND_NULL);
        }
        collectFormMapper.cancel(res);
    }


    /**
     * 根据用户id查询收藏
     * @return
     */
    @Override
    public CollectFormVo getCollectForm(Long id) {
        List<CollectFormVo> collectForm = collectFormMapper.getCollectForm(id);

        List<MusicFormSimpleVo> musicFormSimpleVos = collectForm.stream()
                .filter(collectFormVo -> collectFormVo != null && collectFormVo.getMusicFormList() != null)
                .flatMap(collectFormVo -> collectFormVo.getMusicFormList().stream())
                .collect(Collectors.toList());

        List<SingerSimpleVo> singerSimpleVos = collectForm.stream()
                .filter(collectFormVo -> collectFormVo != null && collectFormVo.getSingerList() != null)
                .flatMap(collectFormVo -> collectFormVo.getSingerList().stream())
                .collect(Collectors.toList());

        List<AlbumSimpleVo> albumSimpleVos = collectForm.stream()
                .filter(collectFormVo -> collectFormVo != null && collectFormVo.getAlbumList() != null)
                .flatMap(collectFormVo -> collectFormVo.getAlbumList().stream())
                .collect(Collectors.toList());

        return CollectFormVo.builder().musicFormList(musicFormSimpleVos).albumList(albumSimpleVos).singerList(singerSimpleVos).build();
    }
}
