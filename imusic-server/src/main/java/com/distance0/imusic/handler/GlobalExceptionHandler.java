package com.distance0.imusic.handler;

import com.distance0.imusic.exception.BaseException;
import com.distance0.imusic.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午10:51
 * @description: 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public R exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return R.error(ex.getMessage());
    }


}
