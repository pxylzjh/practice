package com.pxy.thread.interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author puxy
 * @version 1.0
 * @description 两个线程交替打印ab 100次，并且保证a先打印
 * @date 2023/7/25 16:12
 */
public class 两个线程交替打印ab100次 {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger count = new AtomicInteger(100);




        t2 = new Thread(() -> {
            while (count.get() > 0) {
                LockSupport.unpark(t1);
                LockSupport.park();
                count.getAndDecrement();
                System.out.println("b-"+count.get());
            }
        });

        t1 = new Thread(() -> {
            while (count.get() > 0) {
                LockSupport.park();
                count.getAndDecrement();
                System.out.println("a-"+count.get());
                LockSupport.unpark(t2);
            }
        });

        t2.start();
        t1.start();

    }


}
