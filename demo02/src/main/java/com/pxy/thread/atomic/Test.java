package com.pxy.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author puxy
 * @version 1.0
 * @description 学习 atomic 原子类
 * @date 2022/11/20 23:18
 */
public class Test {

    /**
     * 原子类:
     * 底层也是基于cas以及UNSAFE方法来保证原子性
     *
     */
    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();

        ai.get();

        ai.addAndGet(1);


        ai.incrementAndGet();
        ai.decrementAndGet();

        System.out.println(ai);
    }

}
