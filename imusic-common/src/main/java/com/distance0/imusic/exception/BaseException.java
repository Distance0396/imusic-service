package com.distance0.imusic.exception;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午4:06
 * @description:
 */
public class BaseException extends RuntimeException{

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
