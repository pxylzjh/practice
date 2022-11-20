package com.pxy.thread.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 验证CountDownLatch被唤醒后是顺序执行, 还是竞争锁
 * @date 2022/8/9 15:07
 */
public class CountDownLatchDemo_02 {


    // 结果：被阻塞的线程被唤醒后 需要 竞争锁,谁能强到谁执行,与AQS有关
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(3);

        Runnable r = () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("线程 [%s]开始执行\n", Thread.currentThread().getName());

        };

        for (int i = 0; i < 3; i++) {
            new Thread(r,String.valueOf(i)).start();
            countDownLatch.countDown();
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
