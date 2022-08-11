package com.pxy.thread.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author puxy
 * @version 1.0
 * @description CyclicBarrier
 * @date 2022/8/9 18:24
 */
public class CyclicBarrierDemo_01 {

    /**
     * cyclicBarrier:循环屏障
     * 它允许一组线程相互等待,直到达到屏障点(barrier point)时被拦截的线程从才能继续执行,因为它的barrier可以重用,所以叫它循环屏障
     *
     * 基于{@link java.util.concurrent.locks.ReentrantLock}实现
     *
     */
    public static void main(String[] args) throws Exception {

         test01();

//        test02();

    }

    private static void test02() throws InterruptedException {
        // CyclicBarrier(int parties, Runnable barrierAction) runnable由最后一个线程执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, ()->{
            System.out.printf("线程 [%s] 执行 最终方法\n", Thread.currentThread().getName());
        });

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.printf("线程 [%s] 启动 \n", Thread.currentThread().getName());
                int await;
                try {
                    await = cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("线程 [%s] 执行结束 await = [%d] \n", Thread.currentThread().getName(), await);
            }, String.valueOf(i)).start();
            Thread.sleep(500);
        }
    }

    private static void test01() throws InterruptedException {
        // CyclicBarrier(int parties)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 11; i++) {
            Thread thread = new Thread(() -> {
                System.out.printf("线程 [%s] 启动 \n", Thread.currentThread().getName());
                int await = 0;
                try {
                    await = cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("线程 [%s] 执行结束 await = [%d] \n", Thread.currentThread().getName(), await);
            }, String.valueOf(i));
            thread.start();

            Thread.sleep(500);
        }
    }

}
