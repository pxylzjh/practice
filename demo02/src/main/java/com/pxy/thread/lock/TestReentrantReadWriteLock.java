package com.pxy.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author puxy
 * @version 1.0
 * @description ReentrantReadWriteLock 学习
 * @date 2023/3/7 23:33
 */
public class TestReentrantReadWriteLock {

    public static void main(String[] args) {

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {

                    TimeUnit.SECONDS.sleep(1);

                    readWriteLock.readLock().lock();// 获取读锁

                    System.out.println(Thread.currentThread().getName() + " 获取到锁");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    readWriteLock.readLock().unlock();// 释放锁
                }

            }, "读锁线程 " + i).start();

        }

        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {

                    readWriteLock.writeLock().lock();// 获取读锁
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " 获取到锁");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    readWriteLock.writeLock().unlock();// 释放锁
                }

            }, "写锁线程 " + i).start();

        }


    }
}
