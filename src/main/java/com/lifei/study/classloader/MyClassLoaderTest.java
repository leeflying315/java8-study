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
        try {
            Class<?> aClass = classLoader.loadClass("com.lifei.study.classloader.ClassLoaderDemo");

            System.out.println(aClass.getClassLoader());

            Object test = aClass.newInstance();

            System.out.println(test);

            Method welcomeMethod = aClass.getMethod("welcome");
            String result = (String) welcomeMethod.invoke(test);
            System.out.println("result: " + result);

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
