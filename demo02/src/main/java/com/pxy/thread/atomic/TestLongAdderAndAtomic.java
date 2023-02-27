package com.pxy.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author puxy
 * @version 1.0
 * @description 测试LongAdder和AtomicLong的效率
 * @date 2023/2/26 23:35
 */
public class TestLongAdderAndAtomic {


    public static void main(String[] args) {


        testLongAdder();
        testAtomic();


    }

    private static void testLongAdder() {
        LongAdder longAdder = new LongAdder();
        ExecutorService longAdderEs = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            longAdderEs.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    longAdder.increment();
                }
            });
        }
        longAdderEs.shutdown();
        while (!longAdderEs.isTerminated()) {
        }
        System.out.println("longAdder累加结果：" + longAdder.sum() + " 耗时：" + (System.currentTimeMillis() - start));
    }

    private static void testAtomic() {
        AtomicLong atomicLong = new AtomicLong();
        ExecutorService atomicEs = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            atomicEs.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicLong.incrementAndGet();
                }
            });
        }
        atomicEs.shutdown();
        while (!atomicEs.isTerminated()) {
        }
        System.out.println("atomic累加结果：" + atomicLong.get() + " 耗时：" + (System.currentTimeMillis() - start));
    }

}
