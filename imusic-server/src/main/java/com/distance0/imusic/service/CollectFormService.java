package com.distance0.imusic.service;

import com.distance0.imusic.entity.CollectForm;
import com.distance0.imusic.vo.CollectFormVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author: XiangJing
 * @date: 2024/6/19 上午12:34
 * @description:
 */
public interface CollectFormService {

    /**
     * 收藏
     * @param collectForm
     * @return
     */
    void collect(CollectForm collectForm);

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
    CollectFormVo getCollectForm(Long id);
}
