package com.distance0.imusic.controller.user;

import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.entity.CollectForm;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.CollectFormService;
import com.distance0.imusic.vo.CollectFormVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/18 下午11:27
 * @description:
 */
@Slf4j
@Api(tags = "收藏库接口")
@RestController
@RequestMapping("/user/collect")
public class CollectFormController {

    @Autowired
    private CollectFormService collectFormService;

    /**
     * 收藏
     * @param collectForm
     * @return
     */
    @ApiOperation("添加收藏")
    @PostMapping
    public R collect(@RequestBody CollectForm collectForm) {
        log.info("添加收藏：{}",collectForm);
        collectForm.setUserId(BaseContext.getContextId());
        collectFormService.collect(collectForm);
        return R.success();
    }

    /**
     * 根据用户id查询收藏
     * @return
     */
    @ApiOperation("根据用户id查询收藏")
    @GetMapping
    public R<CollectFormVo> getCollectForm(){
        log.info("根据用户id查询收藏: {}", BaseContext.getContextId());
        CollectFormVo collectForm = collectFormService.getCollectForm(BaseContext.getContextId());
        return R.success(collectForm);
    }

    /**
     * 取消收藏
     * @param collectForm
     * @return
     */
    @PostMapping("/del")
    @ApiOperation("取消收藏")
    public R cancel(@RequestBody CollectForm collectForm) {
        log.info("取消收藏：{}",collectForm);
        collectForm.setUserId(BaseContext.getContextId());
        collectFormService.cancel(collectForm);
        return R.success();
    }

    /**
     * 批量取消收藏
     * @param id
     * @return
     */
    @DeleteMapping("/list")
    @ApiOperation("批量取消收藏")
    public R batchCancel(List<Long> id){
        return R.success();
    }

}
