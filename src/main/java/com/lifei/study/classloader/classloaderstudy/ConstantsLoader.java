package com.lifei.study.classloader.classloaderstudy;

import java.util.Random;

/**
 * @Author: lifei
 * @Description: 常量类加载测试
 * @Date: 2020/11/5
 */
public class ConstantsLoader {
    static {
        System.out.println("the ConstantsLoader will be initialized.");
    }
    // 在其他类中使用MAX不会导致ConstantsLoader类初始化，静态代码块不会输出
    public final static int MAX = 100;

    // 访问类的静态变量，会导致类的初始化
    public static int y = 10;

    // 虽然RANDOM是静态常量，但是由于计算复杂。只有初始化之后才能得到结果。因此在其他类中使用RANDOM会导致ConstantsLoader的初始化
    public final static int RANDOM = new Random().nextInt();
}
