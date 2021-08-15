package com.lifei.study.mybatis;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

/**
 * @Author lifei
 * @Date 2021/8/15
 * @Description:
 */
public class ProxyBeanFactory implements FactoryBean {

    @Override
    public Object getObject() throws Exception {

        // 使用线程类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class[] classes = {IUserDao.class};
        InvocationHandler handler = (proxy, method, args) -> "你被代理了 " + method.getName();

        return Proxy.newProxyInstance(classLoader, classes, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }
}
