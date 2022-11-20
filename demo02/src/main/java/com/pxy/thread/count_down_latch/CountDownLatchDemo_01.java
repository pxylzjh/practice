package com.pxy.thread.count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author puxy
 * @version 1.0
 * @description CountDownLatch 控制线程执行顺序
 * @date 2022/8/9 14:30
 */
public class CountDownLatchDemo_01 {

    // 让线程D等其它线程执行完毕后再执行,即 必须等 count 减为0 被 await()阻塞的线程才能执行
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable r = () -> {
            System.out.printf("线程[%s]开始执行\n", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("线程[%s]执行完毕\n", Thread.currentThread().getName());
            countDownLatch.countDown();

        };

        for (int i = 0; i < 3; i++) {
            new Thread(r, String.valueOf(i)).start();
        }

        Thread threadD = new Thread(()->{
            System.out.printf("线程[%s]开始执行 等待唤醒\n", Thread.currentThread().getName());
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("线程[%s]执行结束\n", Thread.currentThread().getName());
        },"D");


        threadD.start();

    }

}
