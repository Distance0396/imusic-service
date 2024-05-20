package com.distance0.imusic.exception;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午4:35
 * @description:
 */
public class AccountLockedException extends BaseException{
    public AccountLockedException() {
    }

    public AccountLockedException(String message) {
        super(message);
    }
}
