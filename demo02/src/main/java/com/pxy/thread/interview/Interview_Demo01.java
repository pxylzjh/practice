package com.pxy.thread.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author puxy
 * @version 1.0
 * @description 面试题：<p>A线程打印A<p>B线程打印B <p> C线程打印C<p> 一共打印 100 个字符
 * @date 2022/8/20 23:05
 */
public class Interview_Demo01 {

    /**
     * 描述：
     * A线程打印A
     * B线程打印B
     * C线程打印C
     * 一共打印 100 个字符
     */


    private static volatile int count = 1;

    private static Object lock = new Object();

    public static void main(String[] args) {
        solutionTwo();

//        solutionOne();

    }

    /**
     * 方式2：加锁
     */
    private static void solutionTwo() {
        Thread threadA = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count % 3 == 1 && count <= 100) {
                        System.out.println("A:" + count);
                        count++;
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count % 3 == 2 && count <= 100) {
                        System.out.println("B:" + count);
                        count++;
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count % 3 == 0 && count <= 100) {
                        System.out.println("C:" + count);
                        count++;
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    /**
     * 方式1：采用volatile实现
     */
    private static void solutionOne() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        atomicInteger.addAndGet(1);
        Thread threadA = new Thread(() -> {
            while (true) {

                if (count % 3 == 1 && count <= 100) {
                    System.out.println("A:" + count);
                    count++;
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {

                if (count % 3 == 2 && count <= 100) {
                    System.out.println("B:" + count);
                    count++;
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {

                if (count % 3 == 0 && count <= 100) {
                    System.out.println("C:" + count);
                    count++;

                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }


}
