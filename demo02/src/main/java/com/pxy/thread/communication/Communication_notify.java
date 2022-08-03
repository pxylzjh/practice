package com.pxy.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puxy
 * @version 1.0
 * @description 线程间通信 消息传递 - notify 唤醒线程
 * @date 2022/8/3 11:01
 */
public class Communication_notify {

    private static final Object object = new Object();

    //==================================================
    // wait() 和 notify()必须配合 synchronized 使用,不然会报错 java.lang.IllegalMonitorStateException
    //
    //==================================================

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
                    list.add(i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("线程A给list添加元素size=%d \n", i + 1);
                    if (list.size() == 5) {
                        // 唤醒所有 调用过 wait()方法的线程,notify()并不释放锁,只是告诉调用过wait()方法的线程可以去竞争锁了,所以需要等待线程A执行完毕后,线程B才会继续执行
                        object.notify();
                        System.out.println("线程A执行notify(),唤醒线程B,但是不释放锁");
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (object) {
                if (list.size() != 5) {
                    try {
                        System.out.println("线程B启动,等待唤醒");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("线程B被唤醒,并且获取到锁开始执行");
            }

        });


        threadB.start();

        Thread.sleep(1000);

        threadA.start();

    }

}
