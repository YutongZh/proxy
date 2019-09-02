package com.yt.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理核心：
 * 1.Proxy.newProxyInstance
 * 2.参数clazz.getInterfaces()强制规定必须是一个接口
 * 3.必须实现InvocationHandler接口 invoke方法调用
 * 4.生成的为实现被代理的对象对应接口的实现
 */
public class JdkBoss implements InvocationHandler {

    private JdkPerson jdkPerson;

    public Object getInstance(JdkPerson jdkPerson) {
        this.jdkPerson = jdkPerson;
        Class<? extends JdkPerson> clazz = jdkPerson.getClass();
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是boss代理");
        method.invoke(jdkPerson, args);
        System.out.println("安排面试了");
        return null;
    }
}
