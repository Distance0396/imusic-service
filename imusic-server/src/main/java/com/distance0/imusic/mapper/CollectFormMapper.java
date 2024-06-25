package com.distance0.imusic.mapper;

import com.distance0.imusic.entity.CollectForm;
import com.distance0.imusic.vo.CollectFormVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/8 下午2:13
 * @description:
 */
@Mapper
public interface CollectFormMapper {

    /**
     * 添加收藏
     * @param collectForm
     */
    void addCollectForm(CollectForm collectForm);

    /**
     * 查找收藏
     * @param collectForm
     */
    CollectForm selectCollectForm(CollectForm collectForm);

    /**
     * 取消收藏
     * @param collectForm
     * @return
     */
    void cancel(CollectForm collectForm);

    /**
     * 根据用户id查询收藏
     * @return
     */
    List<CollectFormVo> getCollectForm(Long id);
}
