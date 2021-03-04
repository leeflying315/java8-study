package com.lifei.study.classloader.threadClassLoader;

import static java.lang.Thread.currentThread;

/**
 * @Author lifei
 * @Description: 线程上下文类加载器
 * @Date 2021/2/8
 */
public class ThreadClassLoader {

    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());
    }
}
