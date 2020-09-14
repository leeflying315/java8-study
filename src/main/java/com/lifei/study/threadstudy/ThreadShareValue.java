package com.lifei.study.threadstudy;

/**
 * @Author: lifei
 * @Description:
 * @Date: 2020/9/14
 */
public class ThreadShareValue {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    // 可以继承父线程的变量值
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("threadLocal主线程的值");
        Thread.sleep(100);
        new Thread(() -> System.out.println("子线程获取threadLocal的主线程值：" + threadLocal.get())).start();
        Thread.sleep(100);
        inheritableThreadLocal.set("inheritableThreadLocal主线程的值");
        new Thread(() -> System.out.println("子线程获取inheritableThreadLocal的主线程值：" + inheritableThreadLocal.get())).start();

    }
}
