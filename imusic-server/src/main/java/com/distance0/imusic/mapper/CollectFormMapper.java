package com.distance0.imusic.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author: XiangJing
 * @date: 2024/6/8 下午2:13
 * @description:
 */
@Mapper
public interface CollectFormMapper {

    void addCollectForm(Long userId, Long musicFormId);
}
