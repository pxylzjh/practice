package com.pxy.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 学习ReentrantLock
 * @date 2022/11/17 23:47
 */
public class TestReentrantLock {

    public static void main(String[] args) {


        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                int queueLength = lock.getQueueLength();
                lock.lock();
                System.out.println(queueLength + "--");

                lock.unlock();

                System.out.println(queueLength + "++");
            });
            thread.start();
        }


    }

}
