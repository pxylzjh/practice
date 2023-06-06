package com.pxy.thread.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description Condition 实现 3个线程 依次输出 ABC
 * @date 2023/3/2 00:16
 */
public class Condition_ABC {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition_A = lock.newCondition();
    static Condition condition_B = lock.newCondition();
    static Condition condition_C = lock.newCondition();

    static String str = "A";


    public static void main(String[] args) {

        // 线程 1
        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < 3; i++) {

                    while (str != "A")
                        condition_A.await();

                    System.out.println(Thread.currentThread().getName() + "  打印 " + str);
                    str = "B";
                    condition_B.signal();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }).start();

        // 线程 2
        new Thread(()->{

            lock.lock();

            try {
                for (int i = 0; i < 3; i++) {

                    while (str != "B")
                        condition_B.await();

                    System.out.println(Thread.currentThread().getName() + "  打印 " + str);
                    str = "C";
                    condition_C.signal();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }).start();

        // 线程 3
        new Thread(()->{

            lock.lock();

            try {

                for (int i = 0; i < 3; i++) {
                    while (str !="C")
                        condition_C.await();

                    System.out.println(Thread.currentThread().getName() + "  打印 "+ str);
                    str = "A";
                    TimeUnit.SECONDS.sleep(1);
                    condition_A.signal();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }).start();

    }



}
