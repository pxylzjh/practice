package com.pxy.thread.interrupt;

/**
 * @author puxy
 * @version 1.0
 * @description 线程中断  waiting 状态调用 interrupt 直接抛出 InterruptedException
 * @date 2022/8/11 11:23
 */
public class Interrupt_Waiting {

    /**
     * 线程中断：
     * 调用thread.interrupt()方法中断一个线程,将该线程的中断标志位设置为true
     * 中断标志位为true时,在调用sleep、wait、join等阻塞方法的地方会抛出InterruptedException
     *
     * 为什么 线程处于 WAITING状态会抛出InterruptedException异常呢?
     * 其实还是因为 "任何线程都没有权利终止一个线程的生命",一个 WAITING状态的线程 没有CPU的使用权,所以它永远无法知道自己被中断了
     *
     * 那么我们在调用interrupt方法中断一个线程,当发现他是WAITING状态时,将他唤醒并修改指令寄存器指向异常代码块抛出异常,让他自己来处理中断
     *
     * thread.isInterrupted()判断是否被中断,不会清除中断标志位(即将标志位设置为false)
     *
     * Thread.interrupted()也是判断是否被中断,不过会立即清除中断标志位
     *
     * 所以调用interrupt方法是否会抛出异常取决于当前线程是否时WAITING状态
     */

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(){
            @Override
            public void run(){
                synchronized (obj){
                    try {
                        // 调用wait方法让线程进入 WAITING 状态
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
        //主线程等待 thread 线程获取 obj 对象锁并阻塞自己到等待队列
        Thread.sleep(2000);
        thread.interrupt();
    }
}
