package com.lifei.study.classloader.classloaderstudy;

/**
 * @Author lifei
 * @Description: 类加载过程详解
 * @Date 2021/2/3
 */
public class SingletonDemo {

    // 初始化SingletonDemo会初始化X Y,然后XY 又会被重新赋值
    // (静态代码块—>非静态代码块—>构造方法)。
    //　静态代码块只在第一次被类加载器加载时执行一次，之后不再执行，而非静态代码块在每new一次就执行一次
    private static SingletonDemo instance = new SingletonDemo();

    private static int x = 0;

    private static int y;


    private SingletonDemo(){
        y++;
        x=1;
    }

    public static SingletonDemo getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getInstance();
        System.out.println(x);
        System.out.println(singletonDemo.y);
        System.out.println(SingletonDemo.x);

    }
}
