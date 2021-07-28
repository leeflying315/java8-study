package com.lifei.study.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author lifei
 * @Description:
 * @Date 2021/2/3
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        MyClassLoader2 classLoader2 = new MyClassLoader2();
        try {
            Class<?> aClass = classLoader.loadClass("com.lifei.study.classloader.ClassLoaderDemo");
            Class<?> aClass2 = classLoader.loadClass("com.lifei.study.classloader.ClassLoaderDemo2");

            System.out.println(aClass.getClassLoader());
            System.out.println(aClass2.getClassLoader());

            Object test = aClass.newInstance();
            Object test2 = aClass2.newInstance();

            System.out.println(test);
            System.out.println(test2);

            Method welcomeMethod = aClass.getMethod("welcome");
            String result = (String) welcomeMethod.invoke(test);
            System.out.println("result: " + result);

            Method welcomeMethod2 = aClass2.getMethod("welcome");
            String result2 = (String) welcomeMethod2.invoke(test2);
            System.out.println("result: " + result2);

            Method welcomeMethod3 = aClass2.getMethod("classLoaderDemoTest");

            String result3 = (String) welcomeMethod3.invoke(test2);
            System.out.println("result: " + result3);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
