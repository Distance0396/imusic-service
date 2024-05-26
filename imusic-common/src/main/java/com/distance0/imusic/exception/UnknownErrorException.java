package com.distance0.imusic.exception;

/**
 * @author: XiangJing
 * @date: 2024/5/25 下午1:23
 * @description:
 */
public class UnknownErrorException extends BaseException{
    public UnknownErrorException() {
    }

    public UnknownErrorException(String message) {
        super(message);
    }
}
