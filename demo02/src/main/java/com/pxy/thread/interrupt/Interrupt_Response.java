package com.pxy.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 响应中断
 * @date 2022/11/19 19:52
 */

public class Interrupt_Response {

    /**
     * 响应中断是指不断的检测中断标识位,如果线程中断了则执行对应的操作
     *
     * 通过标识位或者中断操作的方式能够使线程在终止时有机会释放资源,这种终止线程的方式更优雅
     */
    public static void main(String[] args) throws InterruptedException {


        stop();


    }

    /**
     * 采用响应中断的方式 停止 线程
     * @throws InterruptedException
     */
    private static void stop() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i="+i);
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);// 让thread充分启动

        thread.interrupt();// 主线程中断 thread
    }


}

