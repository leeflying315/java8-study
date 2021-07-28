package com.lifei.study.classloader;

/**
 * @Author lifei
 * @Description: 类加载器概述—— 1. 根加载器 2. 扩展类加载器 3. 系统类加载器
 * @Date 2021/2/3
 */
public class ClassLoaderDemo2 {

    public static void main(String[] args) {
        // 根加载器
        System.out.println("Bootstrap: "+ String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));

        // 扩展类加载器
        System.out.println(System.getProperty("java.ext.dirs"));


        // 系统类加载器
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ClassLoaderDemo2.class.getClassLoader());
    }

    public String welcome(){
        String s = "world";
        return s;
    }

    public String classLoaderDemoTest(){

        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        return classLoaderDemo.welcome();
    }
}
