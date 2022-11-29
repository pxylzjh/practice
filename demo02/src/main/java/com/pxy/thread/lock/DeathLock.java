package com.pxy.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 实现死锁
 * @date 2022/11/29 17:23
 */
public class DeathLock {


    public static void main(String[] args) {

        // synchronized 实现死锁
//        deathLockOfSync();
        // reentrantLock 实现死锁
        deathLockOfReentrantLock();


    }

    private static void deathLockOfReentrantLock() {
        ReentrantLock lockA = new ReentrantLock();

        ReentrantLock lockB = new ReentrantLock();

        Thread threadA = new Thread(() -> {

            // 获取 lockA
            lockA.lock();
            // 睡眠1s确保 threadB获取到 lockB
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                // 获取 lockB 然后释放锁
                lockB.lock();
                System.out.println(Thread.currentThread().getName());
            } finally {
                lockA.unlock();
                lockB.unlock();
            }


        }, "threadA");


        Thread threadB = new Thread(() -> {

            // 获取 lockB
            lockB.lock();
            try {
                // 获取 lockA 然后释放锁
                lockA.lock();
                System.out.println(Thread.currentThread().getName());
            } finally {
                lockA.unlock();
                lockB.unlock();
            }

        }, "threadB");

        threadA.start();
        threadB.start();
    }

    private static void deathLockOfSync() {
        Object lockA = new Object();

        Object lockB = new Object();

        Thread threadA = new Thread(() -> {

            // 获取 lockA
            synchronized (lockA) {
                // 睡眠1s确保 threadB获取到 lockB
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 获取 lockB
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName());
                }
            }


        }, "threadA");


        Thread threadB = new Thread(() -> {

            // 获取 lockB
            synchronized (lockB) {
                // 获取 lockA
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName());
                }
            }

        }, "threadB");

        threadA.start();
        threadB.start();
    }


}
