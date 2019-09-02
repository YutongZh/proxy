package com.yt.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理 （继承）
 */
public class CglibBoss implements MethodInterceptor{

    public Object getInstance (Class<?> clazz) throws Exception{

        Enhancer enhancer = new Enhancer();
        //选择要继承的父类
        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    /*
     * 需要代理处理的方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理");
        methodProxy.invokeSuper(o, objects);

        System.out.println("cglib 安排面试");
        return null;
    }
}
