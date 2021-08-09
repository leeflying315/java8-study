package com.lifei.designPattern.observer;

/**
 * @Author lifei
 * @Date 2021/8/1
 * @Description:
 */
public interface TaskLifecycle<T> {

    // 任务启动触发onstart方法
    void onStart(Thread thread);

    // 任务正在运行触发OnRunning方法
    void onRunning(Thread thread);

    // 任务运行结束时会触发onFinish方法， 其中result是任务执行结束后的结果
    void onFinish(Thread thread, T result);

    // 任务执行失败的时候触发onError方法
    void onError(Thread thread, Exception ex);

    // 空实现
    class EmptyLifecycle<T> implements  TaskLifecycle<T>{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception ex) {

        }
    }
}
