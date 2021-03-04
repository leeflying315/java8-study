package com.lifei.study.classloader.classloaderstudy;

/**
 * @Author lifei
 * @Description: 类加载过程详解
 * @Date 2021/2/3
 */
public class SingletonDemo {

    // 初始化SingletonDemo会初始化X Y,然后XY 又会被重新赋值
    private static SingletonDemo instance = new SingletonDemo();

    private static int x = 0;

    private static int y;


    private SingletonDemo(){
        x++;
        y++;
    }

    public static SingletonDemo getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getInstance();
        System.out.println(singletonDemo.x);
        System.out.println(singletonDemo.y);
    }
}
