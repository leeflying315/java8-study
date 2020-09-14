package com.lifei.study.lockstudy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lifei
 * @Description:
 * @Date: 2020/9/14
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        // true 代表是公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);

    }
}
