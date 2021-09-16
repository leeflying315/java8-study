package com.lifei.study.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author lifei
 * @Description:
 * @Date 2021/9/16
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    private Object bean;

    public CglibProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy(){
        //设置需要创建子类的类
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }
    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("wakeup")){
            System.out.println("早安~~~");
        }else if(methodName.equals("sleep")){
            System.out.println("晚安~~~");
        }

        //调用原bean的方法
        return method.invoke(bean,args);
    }

    public static void main(String[] args) {
//        CglibProxy proxy = new CglibProxy(new Student("张三"));
//        Student student = (Student) proxy.getProxy();
//        student.wakeup();
//        student.sleep();
//
//        proxy = new CglibProxy(new Doctor("王教授"));
//        Doctor doctor = (Doctor) proxy.getProxy();
//        doctor.wakeup();
//        doctor.sleep();
//
//        proxy = new CglibProxy(new Dog("旺旺"));
//        Dog dog = (Dog) proxy.getProxy();
//        dog.wakeup();
//        dog.sleep();
//
//        proxy = new CglibProxy(new Cat("咪咪"));
//        Cat cat = (Cat) proxy.getProxy();
//        cat.wakeup();
//        cat.sleep();
    }
}
