package com.pxy.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puxy
 * @version 1.0
 * @description 线程间通信 - volatile
 * @date 2022/8/1 18:12
 */
public class Communication_Volatile {


    private volatile static boolean notice = false;

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程A给list添加元素size=" + list.size());
                list.add(i);
            }
            // 当size==5时将 notice 修改为true
            while (list.size() == 5) {
                notice = true;
            }

        });

        Thread threadB = new Thread(() -> {
            System.out.println("线程B启动,等待线程A通知");
            while (true) {
                if (notice) {
                    System.out.println("线程B收到线程A通知,开始执行");
                }
            }

        });

        threadB.start();
        // 让主线程 等待 1s 确保线程B先执行
        Thread.sleep(1000);
        threadA.start();

    }

}
