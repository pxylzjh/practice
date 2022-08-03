package com.pxy.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puxy
 * @version 1.0
 * @description 线程间通信 内存共享 - volatile
 * @date 2022/8/1 18:12
 */
public class Communication_volatile {


    private volatile static boolean notice = false;
    // 当list被volatile修饰时 线程A往list中添加元素后,操作系统就会通知线程B重新去内存中加载list
    // private volatile static List<Integer> list = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程A给list添加元素size=" + list.size());
                list.add(i);
                // 当size==5时将 notice 修改为true
                if (list.size() == 5) {
                    notice = true;
                }
            }

        });

        Thread threadB = new Thread(() -> {
            System.out.println("线程B启动,等待线程A通知");
            while (true) {
                // 不能用 用list.size()来判断,因为list被线程B加载到栈的时候 list是空的,由于list并没有被volatile修饰所以当线程A添加元素后并不会通知线程B重新从内存加载list
//                if (list.size() == 5) {
                if (notice) {
                    System.out.println("线程B收到线程A通知,开始执行");
                    break;
                }
            }

        });

        threadB.start();
        // 让主线程 等待 1s 确保线程B先执行
        Thread.sleep(1000);
        threadA.start();

    }

}
