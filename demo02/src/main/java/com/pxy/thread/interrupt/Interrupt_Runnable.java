package com.pxy.thread.interrupt;

/**
 * @author puxy
 * @version 1.0
 * @description 当线程处于RUNNING状态时调用interrupt方法
 * @date 2022/8/11 11:42
 */
public class Interrupt_Runnable {

    public static void main(String[] args) throws InterruptedException {

//        test01();
        // 手动停止线程
        test02();
    }

    private static void test02() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    System.out.println("i=" + i);
                    // 判断如果 中断 标记位 为 true,手动停止线程
                    if (this.isInterrupted()) {
                        break;
                    }
                }
            }
        };

        thread.start();
        thread.interrupt();
        thread.join();
    }

    private static void test01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                // 当线程处于 RUNNING 状态时,就算线程中断标志位为true并不会立即中断线程,java提倡"一个线程的生命不该由其他线程终止,应该由它自己决定是否停止"
                // 所以当主线程调用 interrupt方法后 线程依然能成功打印5000个数字
                System.out.println("i=" + i);
            }
        });
        thread.start();
        thread.interrupt();
        thread.join();
    }

}
