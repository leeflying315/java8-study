package com.lifei.study.threadstudy;

/**
 * @Author: lifei
 * @Description: 线程本地变量测试
 * @Date: 2020/9/14
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "java金融");
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        System.out.println("获取初始值：" + threadLocal.get());
        threadLocal.set("关注：【java金融】");
        System.out.println("获取修改后的值：" + threadLocal.get());
        threadLocal.remove();
        hashCode(16); //初始化16
        hashCode(32); //后续2倍扩容
        hashCode(64);
    }



    private static void hashCode(Integer length) {
        int hashCode = 0;
        for (int i = 0; i < length; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;//每次递增HASH_INCREMENT
            System.out.print(hashCode & (length - 1));
            System.out.print(" ");
        }
        System.out.println();
    }
}
