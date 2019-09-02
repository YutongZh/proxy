package com.yt.proxy.cglibproxy;

import com.yt.proxy.jdkproxy.JdkPerson;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * cglib 动态代理
 */
public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        CglibBoss cglibBoss = new CglibBoss();
        CglibChyson instance = (CglibChyson)cglibBoss.getInstance(CglibChyson.class);
        instance.findJob();
        System.out.println(instance.getClass());

        //从jvm取出反编译的对象  字节码保存为文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{JdkPerson.class});
        FileOutputStream fo = new FileOutputStream("/Users/yutong/temp/proxyCglib.class");
        fo.write(bytes);
        fo.close();
    }
}
