package com.distance0.imusic.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午2:57
 * @description:
 */

/**
 * 结果类
 * @param <T>
 */
@Data
public class R<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 400;
        return r;
    }
}
