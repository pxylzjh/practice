package com.pxy.thread.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author puxy
 * @version 1.0
 * @description 学习LockSupport
 * @date 2022/11/17 14:44
 */
public class TestLockSupport {

    /**
     * LockSupport:
     * 底层是通过UNSAFE的方法直接修改内存地址的值来实现线程的阻塞,可以使线程进入WAITING 或这 TIMED_WAITING
     * 并且支持主线程先调用unPark 然后 线程A再调用park方法
     *
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

//            LockSupport.parkNanos(5_000000_000L);
            LockSupport.park();
            System.out.println("被中断了，但是没有抛出中断异常");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("调用sleep后被中断，抛出中断异常");
                throw new RuntimeException(e);
            }
            System.out.println("xxxxxxx");

        });

        thread.start();

        System.out.println(thread.isInterrupted());
        Thread.sleep(1000);//1s
        System.out.println(thread.getState());
        // LockSupport.park() 使线程进入WAITING 或者 TIMED_WAITING 状态,但是调用interrupt方法时候 不会像 Thread.sleep/join、Object.wait一样抛出InterruptedException
        thread.interrupt();
        Thread.sleep(1000);//1s
        System.out.println(thread.isInterrupted());

        LockSupport.parkNanos(3_000_000_000L); // 3s
//        LockSupport.unpark(thread);
        Thread.State state = thread.getState();
        System.out.println("主线程结束"+state);

    }

}
