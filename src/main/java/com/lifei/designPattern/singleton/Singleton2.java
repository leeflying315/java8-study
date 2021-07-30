package com.lifei.designPattern.singleton;

/**
 * @Author lifei
 * @Date 2021/7/29
 * @Description:
 * 懒汉式 并不能保证单例的唯一性
 */
public final class Singleton2 {
    private byte[] data = new byte[1024];

    private static Singleton2 instance = null;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (null == instance) {
            instance = new Singleton2();
        }
        return instance;
    }
}
