package com.yt.proxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * jdk动态代理
 */
public class JdkProxyTest {

    public static void main(String[] args) throws IOException {
        JdkPerson jdkPerson = new JdkChyson();
        JdkBoss jdkBoss = new JdkBoss();
        JdkPerson obj = (JdkPerson)jdkBoss.getInstance(jdkPerson);
        //obj事实为JdkChyson其的代理对象，实现了jdkPerson所有方法
        //class com.sun.proxy.$Proxy0 自动生成代理类
        System.out.println(obj.getClass());

        obj.findJob();
        /**字节码重组*/
        /*1.拿到被代理对象的引用，并且获取到它的所有接口，反射获取
        2.jdk proxy类重新生成一个新的类，同时新的类要实现被代理类的所有实现，
        3.动态生成java代码，把新加的业务逻辑方法由一定的逻辑代码去调用
        4.编译新生成的java代码 .class 文件
        5.再重新加载到jvm中运行*/

        //从jvm取出反编译的对象  字节码保存为文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy1", new Class[]{JdkPerson.class});
        FileOutputStream fo = new FileOutputStream("/Users/yutong/temp/proxy0.class");
        fo.write(bytes);
        fo.close();
    }
}
