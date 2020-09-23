package com.lifei.study.threadstudy;

import java.util.concurrent.*;

/**
 * @Author: lifei
 * @Description: 线程异常打印处理
 * 线程池会移除异常线程，并创建一个新的到线程池中。
 * @Date: 2020/9/21
 */
public class ThreadExceptionStudy {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        // 不会报错
        Future future= threadPoolExecutor.submit(() -> {
            sayHi("execute");
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 会打印异常
        threadPoolExecutor.execute(() -> {
            sayHi("execute");
        });
    }

    public static void sayHi(String value) {
        System.out.println("thread name :" + Thread.currentThread().getName() + "\t" + value);
        throw new RuntimeException("exception occurs");
    }
}
