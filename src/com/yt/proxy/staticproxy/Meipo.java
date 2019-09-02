package com.yt.proxy.staticproxy;

/**
 *  核心：代理类持有被代理类的引用。
 */
public class Meipo {

    private Chyson chyson;

    public Meipo(Chyson chyson) {
        this.chyson = chyson;
    }

    public void findLove(){
        System.out.println("想找个什么对象");
        chyson.findLove();
        System.out.println("约双方见面了！");
    }
}
