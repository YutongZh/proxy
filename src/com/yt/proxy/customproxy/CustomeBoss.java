package com.yt.proxy.customproxy;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理核心：
 * 1.Proxy.newProxyInstance
 * 2.参数clazz.getInterfaces()强制规定必须是一个接口
 * 3.必须实现InvocationHandler接口 invoke方法调用
 * 4.生成的为实现被代理的对象对应接口的实现
 */
public class CustomeBoss implements CustomeInvocationHandle {

    private CustomePerson customePerson;

    public Object getInstance(CustomePerson customePerson) {
        this.customePerson = customePerson;
        Class<? extends CustomePerson> clazz = customePerson.getClass();
        Object obj = CustomProxy.newProxyInstance(new CustomClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是boss代理");
        method.invoke(customePerson, args);
        System.out.println("安排面试了");
        return null;
    }
}
