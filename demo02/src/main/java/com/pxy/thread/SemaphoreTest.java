package com.pxy.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 学习信号量 Semaphore
 * @date 2023/5/24 00:32
 */
public class SemaphoreTest {

    /**
     * 底层也是基于AQS实现的，许可数其实就是AQS中的state，假设设置许可数为3，每次调用acquire(i)方法都对state-i，如果state-i<0说明许可用完了，
     * 则将当前线程放入AQS的同步队列中阻塞等待，当调用release()方法释放许可后再进行唤醒
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        testNonfair();

        testFair();
    }

    private static void testFair() throws InterruptedException {
        // 公平
        Semaphore semaphore = new Semaphore(3,true);
        for (int i = 0; i < 10; i++) {

            new Thread(()->{

                // 获取许可
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                try {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "执行结束------");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 执行结束 释放许可  让其他线程能够获取 许可
                    semaphore.release();
                }

            }).start();

            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    private static void testNonfair() {
        // 创建信号量  许可为3  只能允许 3个线程同时运行 默认非公平
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {

            new Thread(()->{

                // 获取许可
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                try {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "执行结束------");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 执行结束 释放许可  让其他线程能够获取 许可
                    semaphore.release();
                }

            }).start();


        }
    }

}
