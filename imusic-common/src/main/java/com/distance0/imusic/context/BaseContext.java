package com.distance0.imusic.context;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:09
 * @description:
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setContextId(Long context) {
        threadLocal.set(context);
    }

    public static Long getContextId() {
        return threadLocal.get();
    }

    public static void clearContextId() {
        threadLocal.remove();
    }
}
