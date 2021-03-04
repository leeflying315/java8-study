package com.lifei.study.classloader.classloaderstudy;

/**
 * @Author lifei
 * @Description:
 * @Date 2021/2/3
 */
public class ClassStarter {
    public static void main(String[] args) {
        // 调用静态常量不会导致类初始化
        System.out.println(ConstantsLoader.MAX);
        // 调用静态变量会导致类初始化
//        System.out.println(ConstantsLoader.y);
        // 虽然RANDOM是静态常量，但是由于计算复杂。只有初始化之后才能得到结果。因此在其他类中使用RANDOM会导致ConstantsLoader的初始化
        System.out.println(ConstantsLoader.RANDOM);

    }
}
