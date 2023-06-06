package com.pxy.thread.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 测试 Condition
 * @date 2023/3/1 19:09
 */
public class TestCondition {

    /**
     * 在使用Synchronize加锁的时候，可以结合wait()、notify()、notifyAll()等方法实现 等待/通知模式
     * Condition也提供了类似的方法，与Lock配合使用也可以实现 等待/通知模式
     */
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static volatile List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                lock.lock();

                try {
                    System.out.println(Thread.currentThread().getName() + " 等待唤醒");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + " 被唤醒");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

            }).start();
        }

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.lock();
            try {
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }).start();


    }

}
