package com.lifei.designPattern.singleton;

/**
 * @Author lifei
 * @Date 2021/7/30
 * @Description:
 * Holder方式实现单例模式
 * Singleton 并没有instance成员变量，在初始化过程中并不会创建实例。
 * 当Holder被主动引用的时候则会创建Singleton实例。
 * 实例创建过程中编译时期收集至clinit方法，该方法又是同步方法。
 * 同步方法可以保证内存可见性、JVM指令顺序性和原子性。
 */
public final class Singleton6 {
    private Singleton6() {
    }

    // 在静态内部内中持有Singleton实例，并且可以被直接初始化
    private static class Holder {
        private static Singleton6 instance = new Singleton6();
    }

    // 调用getInstance方法，事实上是获得Holder的Instance静态属性
    public static Singleton6 getInstance() {
        return Holder.instance;
    }
}
