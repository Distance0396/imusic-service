package com.distance0.imusic.exception;

/**
 * @author: XiangJing
 * @date: 2024/6/19 上午12:59
 * @description:
 */
public class AlreadyExistException extends BaseException{
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
