package com.lifei.study.gcstudy;

public class GCDemo
{
    private final static String hello = "hello";

    public static void test()
    {
        // 1.只在常量池上创建常量
        String a1 = "AA";
        // 2.只在堆上创建对象
        String a2 = new String("A") + new String("A");
        //3.在堆上创建对象，在常量池上创建常量
        String a4 = new String("A") + new String("A");//只在堆上创建对象AA
        a4.intern();//将该对象AA的引用保存到常量池上
        //5.在堆上创建对象，在常量池上创建引用, 在常量池上创建常量（不可能）
        String a5 = new String("356");//只在堆上创建对象
        a5.intern();//在常量池上创建引用
        String a6 = "356";//此时不会再在常量池上创建常量AA，而是将a5的引用返回给a6
        System.out.println(a5 == a6); //false

        String s = new String("128");
        s = s.intern(); //需要自己指向常量池创建的引用
        String s2 = "128";
        System.out.println(s == s2);
    }

    public static void main(String[] args) {
//        System.out.println();
        GCDemo.test();
        String str1 = new StringBuilder("计算机").append("软件").toString();
        // JDK7后会复制首次在堆创建的对象的引用到方法区
        System.out.println(str1.intern()==str1);
        // java对象已经在常量区存在，返回的引用与新建的对象引用地址不同，不符合首次出现原则
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
        // 只在常量池上创建常量
        String str3 = "java";
        System.out.println(str3.intern()==str3);

        String str4 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str4.intern()==str4);
    }

}
