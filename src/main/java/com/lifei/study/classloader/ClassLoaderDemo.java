package com.lifei.study.classloader;

/**
 * @Author lifei
 * @Description: 类加载器概述—— 1. 根加载器 2. 扩展类加载器 3. 系统类加载器
 * @Date 2021/2/3
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // 根加载器
        System.out.println("Bootstrap: "+ String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));

        // 扩展类加载器
        System.out.println(System.getProperty("java.ext.dirs"));


        // 系统类加载器
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ClassLoaderDemo.class.getClassLoader());
    }

    public String welcome(){
        // classloader 可以访问初始化类加载器加载的类
        String s = "hello";
        return s;
    }
}
