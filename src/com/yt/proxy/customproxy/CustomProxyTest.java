package com.yt.proxy.customproxy;

public class CustomProxyTest {
    public static void main(String[] args) {
        CustomePerson customePerson = new CustomeChyson();
        CustomeBoss customeBoss = new CustomeBoss();
        CustomePerson obj = (CustomePerson)customeBoss.getInstance(customePerson);
        //obj事实为JdkChyson其的代理对象，实现了jdkPerson所有方法
        //class com.sun.proxy.$Proxy0 自动生成代理类
        System.out.println(obj.getClass());
        obj.findJob();
    }
}
