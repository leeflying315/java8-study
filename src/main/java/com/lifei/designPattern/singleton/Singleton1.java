package com.lifei.designPattern.singleton;

/**
 * @Author lifei
 * @Date 2021/7/29
 * @Description:
 * 饿汉式 资源预先加载
 */
public final class Singleton1 {
    // 实例变量
    // 只要初始化后，就会占用1K空间
    private byte[] data = new byte[1024];

    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
        System.out.println("hello world");
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
