package com.pxy.thread.interrupt;

/**
 * @author puxy
 * @version 1.0
 * @description 线程 Blocked 状态中断
 * @date 2022/8/11 15:18
 */
public class Interrupt_Blocked {

    /**
     * 线程处于BLOCKED状态时,调用interrupt方法只是把中断标记位置为true,但是因为任何线程都无法终止一个线程的生命,所以线程线程依然会处于BLOCKED状态
     *
     * BLOCKED：等待获取锁,不释放CPU使用权
     *
     */
    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread() {
            @Override
            public void run() {
                System.out.println("线程A等待获取锁");
                synchronized (object){
                    System.out.println("线程A获取锁成功");
                    while (this.isInterrupted()) {
                        break;
                    }
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                System.out.println("线程B等待获取锁");
                synchronized (object){
                    System.out.println("线程B获取锁成功");
                }
            }
        };

        threadA.start();
        // 确保A线程先获得锁  B线程被阻塞
        Thread.sleep(1000);
        threadB.start();
        // 线程B 等待获取锁 处于 BLOCKED状态  此时调用interrupt并不会结束线程B
        threadB.interrupt();
        // 3秒后,中断线程A 并释放锁,线程B获取锁
        Thread.sleep(3000);
        threadA.interrupt();
        System.out.println("主线程出栈");

    }

}
