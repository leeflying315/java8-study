package com.lifei.designPattern.singleton;

/**
 * @Author lifei
 * @Date 2021/7/30
 * @Description:
 * 枚举方式
 * 不允许继承，只能被实例化一次。
 * 但是不能够懒加载。调用其中静态方法 INSTANCE 立即被实例化。
 * 可以使用枚举类结合Holder模式
 */
public enum Singleton7 {
    INSTANCE;

    // 实例变量
    private byte[] data = new byte[1024];

    Singleton7() {
        System.out.println("hello world");
    }

    public static void method() {
        // 调用该方法则会主动使用Singleton, INSTANCE将会被实例化
    }

    public static Singleton7 getInstance() {
        return INSTANCE;
    }
}
