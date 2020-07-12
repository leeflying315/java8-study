package com.lifei.algorithm.multitheadingstudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
/*
*  1114. 按序打印
*  解法： 1. 信号量
*        2. 同步
*  */
public class MultitheadingDemo {
    public Semaphore semaphore_first_two = new Semaphore(0);

    public Semaphore semaphore_two_three = new Semaphore(0);

    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);

    public MultitheadingDemo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
//        semaphore_first_two.release();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
//            semaphore_first_two.acquire();
        second.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        third.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {
        third.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
