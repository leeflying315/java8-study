package com.lifei.study.classloader.threadClassLoader;

import com.lifei.study.classloader.MyClassLoader;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author lifei
 * @Description: 线程上下文类加载器
 * @Date 2021/2/8
 */
public class ThreadClassLoader {

    public static void main(String[] args) {
        for(int i =0 ;i<10;i++){
            if(i==5)
                continue;
            System.out.println(i);
        }
        System.out.println(currentThread().getContextClassLoader());
        ArrayList<MyClassLoader> list = new ArrayList<>();
        Long start =System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        try {
            boolean result = countDownLatch.await(10, TimeUnit.SECONDS);
            System.out.println(System.currentTimeMillis() - start);

        } catch (InterruptedException e) {
            System.out.println(e);
            System.out.println(System.currentTimeMillis() - start);
        }

    }
}
