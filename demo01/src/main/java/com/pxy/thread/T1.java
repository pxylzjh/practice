package com.pxy.thread;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author puxy
 * @version 1.0
 * @description 多线程练习
 * @date 2021/2/7 14:31
 */
public class T1 {

    public static void main(String[] args) {

        /**
         * 线程池的创建方法总共有7中,可分为2类
         * 1：使用Executors创建线程池(6种)
         * 2：使用ThreadPoolExecutor创建线程池(1中)
         */
        IntStream.range(0,10).filter(e->e>2).forEach(e-> System.out.println(e));
//        fixedThreadPool();
//        cacheThreadPool();
//        singleThreadPool();
//        scheduledThreadPool();
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> schedule = threadPool.schedule(() -> {
            System.out.println("任务被执行,线程：" + Thread.currentThread().getName());
        }, 4, TimeUnit.SECONDS);
        long delay = schedule.getDelay(TimeUnit.SECONDS);
        boolean cancel = schedule.cancel(true);
        System.out.println(delay);


    }

    private static void scheduledThreadPool() {
        //4.创建一个可执行延时任务的线程池,设置最小线程数为5
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        System.out.println("添加任务时间：" + LocalDateTime.now());
        IntStream.range(0,10).forEach(e->{
            threadPool.schedule(() -> {
                System.out.println(Thread.currentThread().getName()+" 执行任务时间："+LocalDateTime.now());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }, 4, TimeUnit.SECONDS);
        });

    }

    private static void singleThreadPool() {
        //3.创建单个线程数的线程池,它可以保证先进先出的顺序执行
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        IntStream.range(0, 10).forEach(e -> {
            int index = e;
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程：" + Thread.currentThread().getName() + ":" + index);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void cacheThreadPool() {
        //2.创建一个可缓存的线程池，若线程数量超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程
        //默认最大线程数为2^31-1,最大int值，回收时间60s
        ExecutorService threadPool = Executors.newCachedThreadPool();
        IntStream.range(0, 13).forEach(e -> {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程：" + Thread.currentThread().getName());
            });
        });
    }

    private static void fixedThreadPool() {
        //1.创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        IntStream.range(0, 4).forEach(e -> {
            System.out.println(e);
            threadPool.submit(() -> System.out.println("任务被执行,线程：" + Thread.currentThread().getName()));
        });
    }
}
