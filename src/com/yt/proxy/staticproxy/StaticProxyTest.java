package com.yt.proxy.staticproxy;

/**
 *  静态代理
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        Chyson chyson = new Chyson();
        Meipo meipo = new Meipo(chyson);
        meipo.findLove();
        System.out.println("================== ");
        Boss boss = new Boss(chyson);
        boss.findJob();
    }
}
