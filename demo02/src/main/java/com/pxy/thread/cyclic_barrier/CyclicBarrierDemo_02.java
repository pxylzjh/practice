package com.pxy.thread.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author puxy
 * @version 1.0
 * @description 测试  当一个被标记为中断的线程 被barrier拦截的时候  是否会放行已拦截的线程
 * @date 2022/8/11 17:57
 */
public class CyclicBarrierDemo_02 {


    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                System.out.printf("线程 [%s] 开始执行\n", Thread.currentThread().getName());

            });
        }

    }

}
