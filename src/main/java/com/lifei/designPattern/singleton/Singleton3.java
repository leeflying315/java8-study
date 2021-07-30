package com.lifei.designPattern.singleton;

/**
 * @Author lifei
 * @Date 2021/7/29
 * @Description: 懒汉式 + 同步方法
 * 性能较低
 */
public final class Singleton3 {

    private static Singleton3 instance = null;

    private Singleton3() {

    }

    // 加入同步控制，每次只能由一个线程进入
    public static synchronized Singleton3 getInstance() {
        if (null == instance) {
            instance = new Singleton3();
        }
        return instance;
    }
}
