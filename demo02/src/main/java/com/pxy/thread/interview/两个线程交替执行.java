package com.pxy.thread.interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @author puxy
 * @version 1.0
 * @description 两个线程交替执行
 * @date 2023/2/18 21:52
 */
public class 两个线程交替执行 {

    static Thread t1 = null, t2=null;
    public static void main(String[] args) {

        char[] c1 = {'a','b','c','d','e','f','g'};

        char[] c2 = {'1','2','3','4','5','6','7'};



        // 创建2个线程分别 遍历打印 c1 c2
        // 要注意 两个线程 park 和 unpark 的 顺序

        t1 = new Thread(()->{

            for (char c : c1) {
                LockSupport.park();     // t1 park 等待 t2 唤醒
                System.out.println(c);
                LockSupport.unpark(t2); // t1 唤醒 t2
            }

        }, "t1");

        t2 = new Thread(()->{

            for (char c : c2) {
                System.out.print(c);
                LockSupport.unpark(t1); // t2 唤醒 t1
                LockSupport.park();     // t2 park  等待 t1 唤醒
            }

        }, "t2");

        t1.start();
        t2.start();

    }

}
