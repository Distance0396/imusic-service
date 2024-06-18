package com.distance0.imusic.exception;

/**
 * @author: XiangJing
 * @date: 2024/6/17 上午8:38
 * @description:
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException() {}
    public LoginFailedException(String message) {
        super(message);
    }
}
