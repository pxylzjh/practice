package com.pxy.thread.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author puxy
 * @version 1.0
 * @description countDownLatch 实现线程通信
 * @date 2022/8/9 14:13
 */
public class Communication_countDownLatch {


    /**
     * CountDownLatch是基于AQS实现的"倒计数器",它允许一个或多个线程等待其它线程执行完之后再执行
     *
     */

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        List<Integer> list = new ArrayList<>();
        Thread threadA = new Thread(() -> {

            System.out.println("线程A启动");

            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.printf("[线程A]往list添加元素%d\n", i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (list.size() == 5) {
                    countDownLatch.countDown();
                    System.out.println("[线程A]唤醒线程B");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("[线程B]启动,等待唤醒");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("[线程B]被唤醒,开始执行");
        });


        Thread threadC = new Thread(() -> {
            System.out.println("[线程C]启动,等待唤醒");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("[线程C]被唤醒,开始执行");
        });

        threadC.start();
        threadB.start();
        Thread.sleep(500);
        threadA.start();
    }

}
