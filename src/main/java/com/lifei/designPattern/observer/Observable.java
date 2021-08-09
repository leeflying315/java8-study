package com.lifei.designPattern.observer;

/**
 * @Author lifei
 * @Date 2021/8/1
 * @Description:
 *  观察者模式
 */
public interface Observable {
    // 任务周期的枚举类型s
    enum Cycle{
        STARTED, RUNNING, DONE, ERROR
    }

    // 获取当前任务的生命周期状态
    Cycle getCycle();

    // 定义启动线程的方法，主要是为了屏蔽Thread的其他方法
    void start();

    // 定义线程的打断方法，作用同start一样。
    void interrupt();
}
