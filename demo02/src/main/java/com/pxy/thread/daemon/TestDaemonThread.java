package com.pxy.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 学习Daemon线程  (常说的守护线程)
 * @date 2022/11/20 16:11
 */
public class TestDaemonThread {

    /**
     * daemon线程: 是一种支持型线程,主要用作程序中后台调度以及支持性工作
     * 1.当java虚拟机中不存在非Daemon线程时,java虚拟机将会退出
     * 2.java虚拟机退出时Daemon线程中的finally代码块不一定会执行
     *
     */
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("finally 代码块执行了");// thread 为 Daemon线程 不会输出
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}
