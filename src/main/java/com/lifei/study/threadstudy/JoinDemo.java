package com.lifei.study.threadstudy;

/**
 * @Author: lifei
 * @Description: 多线程join学习
 * @Date: 2020/11/3
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new TestThread();
        Thread t2 = new TestThread();
        Thread t3 = new TestThread();

        t1.start();
        t3.start();
        //t.join()方法只会使主线程进入等待池并等待t线程执行完毕后才会被唤醒。
        // 并不影响同一时刻处在运行状态的其他线程。
        t1.join();
        System.out.println("hello");
        t2.start();
    }

    static class TestThread extends Thread {
        @Override
        public void run() {
            for(int temp=0;temp<5;temp++) {
                System.out.println(Thread.currentThread().getName() + "\t" + temp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
