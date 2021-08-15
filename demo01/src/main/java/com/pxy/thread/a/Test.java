package com.pxy.thread.a;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/7/5 17:09
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        /**
         *
         * 3个线程 t1,t2,t3
         * 保证 t2在t1之后执行,t3在t2之后执行
         *
         * 下面采用了join来实现
         *  join的作用,父线程在子线程执行完之后执行
         *
         */
        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName());
        };

        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        Thread t3 = new Thread(r,"t3");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();

    }

}
