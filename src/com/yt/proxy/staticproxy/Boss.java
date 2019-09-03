package com.yt.proxy.staticproxy;

public class Boss implements Person{
    private Chyson chyson;

    public Boss(Chyson chyson) {
        this.chyson = chyson;
    }

    @Override
    public void findJob(){
        System.out.println("想找什么样的工作");
        chyson.findJob();
        System.out.println("约面试！");
    }
}
