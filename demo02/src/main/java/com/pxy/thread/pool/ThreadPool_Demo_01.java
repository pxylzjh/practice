package com.pxy.thread.pool;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author puxy
 * @version 1.0
 * @description 多线程练习_线程池
 * @date 2021/2/7 14:31
 */
public class ThreadPool_Demo_01 {

    public static void main(String[] args) {

        /**
         * 线程池的创建方法总共有7中,可分为2类
         * 1：使用Executors创建线程池(6种)
         * 2：使用ThreadPoolExecutor创建线程池(1种)
         */

        fixedThreadPool();
//        cacheThreadPool();
//        singleThreadPool();
//        scheduledThreadPool();
//        singleThreadScheduledExecutor();
//        workStealingPool();
//        threadPoolExecutor_default();
//        threadPoolExecutor_DiscardPolicy();
//        threadPoolExecutor_CallerRunsPolicy();
//        threadPoolExecutor_DiscardOldestPolicy();
        threadPoolExecutor_MyReject();
    }

    private static void threadPoolExecutor_MyReject() {
        //自定义拒绝策略：
        //创建一个线程池，设置核心线程数和最大线程数相等，即大小固定为6
        //此时设置任务队列大小为2，并创建10个线程，但是线程池总的容量为 最大线程数+任务队列长度
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 6,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("自定义拒绝策略");
            }
        });
        IntStream.range(0,12).forEach(e->{
            threadPool.execute(()->{
                System.out.println(e+"被执行,线程名：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void threadPoolExecutor_DiscardOldestPolicy() {
        //抛弃头部任务(最老的任务)，并执行当前任务，所以输出结果中，始终没有6和7被输出，因为从6开始，线程数已经到达了核心线程数，所以线程进入任务队列排队，
        // 此时又有新的任务进来，因为队列长度为3，超过了队列长度，且超过了最大线程数，采取拒绝策略，放弃之前的，将最新的放入队列，所以最后输出的个数始终等于队列长度
        //创建一个线程池，设置核心线程数和最大线程数相等，即大小固定为6
        //此时设置任务队列大小为2，并创建10个线程，但是线程池总的容量为 最大线程数+任务队列长度
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 6,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),new ThreadPoolExecutor.DiscardOldestPolicy());
        IntStream.range(0,12).forEach(e->{
            threadPool.execute(()->{
                System.out.println(e+"被执行,线程名：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void threadPoolExecutor_CallerRunsPolicy() {
        //使用当前线程来执行：即不使用线程池的线程，而是使用当前调用者线程来执行，如使用main方法的话，就是使用主线程
        //创建一个线程池，设置核心线程数和最大线程数相等，即大小固定为6
        //此时设置任务队列大小为2，并创建10个线程，但是线程池总的容量为 最大线程数+任务队列长度
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 6,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2),new ThreadPoolExecutor.CallerRunsPolicy());
        IntStream.range(0,10).forEach(e->{
            threadPool.execute(()->{
                System.out.println(e+"被执行,线程名：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void threadPoolExecutor_DiscardPolicy() {
        //忽略并放弃
        //创建一个线程池，设置核心线程数和最大线程数相等，即大小固定为6
        //此时设置任务队列大小为2，并创建10个线程，但是线程池总的容量为 最大线程数+任务队列长度
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 6,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2),new ThreadPoolExecutor.DiscardPolicy());
        IntStream.range(0,10).forEach(e->{
            threadPool.execute(()->{
                System.out.println(e+"被执行,线程名：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void threadPoolExecutor_default() {
        //默认拒绝策略
        //创建一个线程池，设置核心线程数和最大线程数相等，即大小固定为6
        //此时设置任务队列大小为2，并创建10个线程，但是线程池总的容量为 最大线程数+任务队列长度，所以采取默认拒绝策略，报错并放弃该任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 6,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2));
        IntStream.range(0,10).forEach(e->{
            threadPool.execute(()->{
                System.out.println(e+"被执行,线程名：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
    }

    private static void workStealingPool() {
        //1.创建一个抢占式的线程池，执行任务顺序不确定，只有在jdk1.8+才支持，不知道有啥特别的
        ExecutorService threadPool = Executors.newWorkStealingPool();
        IntStream.range(0, 10).forEach(e -> {
            final int index = e;
            threadPool.execute(() -> {
                System.out.println(index + "被执行,线程名：" + Thread.currentThread().getName());
            });
        });
        // 确保任务执行完成，去掉之后就不执行了，不知道为啥
        while (!threadPool.isTerminated()) {
        }
    }

    private static void singleThreadScheduledExecutor() {
        //1.创建一个单线程,可创建延时任务的线程池
        //2.查看源码可知：该线程池最大核心线程数为corePoolSize=1,最大线程数maximumPoolSize=2^31-1,最大存活时间 0,延时队列容量2^31-1
        //3.通过运行结果可知：从头到尾都只有一个线程在执行，当任务数>1时,会放入到延时队列中,并不会创建新的线程,只有当队列满了后,才会创建新线程,
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        System.out.println("任务被添加,时间：" + LocalDateTime.now());
        IntStream.range(0, 10).forEach(e -> {
            ScheduledFuture<?> schedule = threadPool.schedule(() -> {
                System.out.println("任务被执行,时间：" + LocalDateTime.now());
                System.out.println("任务被执行,线程：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }, 2, TimeUnit.SECONDS);
            //不知道为啥获取到的delay时间比上面指定的小1
            long delay = schedule.getDelay(TimeUnit.SECONDS);
            //取消，线程将不会被执行
//        boolean cancel = schedule.cancel(true);
//            System.out.println(delay);
        });
    }

    private static void scheduledThreadPool() {
        //4.创建一个可执行延时任务的线程池,设置最小线程数为5
        //查看源码可知：核心线程数由用户指定，最大线程maximumPoolSize=2^31-1，存活时间0，所以线程执行完毕后，线程池不会关闭，程序不会终止
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        System.out.println("添加任务时间：" + LocalDateTime.now());
        IntStream.range(0, 10).forEach(e -> {
            threadPool.schedule(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务时间：" + LocalDateTime.now());
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
        //查看源码可知：该线程池核心线程数corePoolSize=1，最大线程maximumPoolSize=1，存活时间0ms，虽然存活时间为0ms，但是线程执行完毕后，程序并没有终止，所以可知线程存活时间keepAliveTime指的是除核心线程外的线程
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
        //查看源码可知：缓存线程池的核心线程数corePoolSize=0,最大线程数maximumPoolSize=2^31-1,线程最大存活时间为60s，所以当线程执行完，60s后线程池将自动关闭，程序终止
        ExecutorService threadPool = Executors.newCachedThreadPool();
        IntStream.range(0, 13).forEach(e -> {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程：" + Thread.currentThread().getName());
            });
        });
    }

    private static void fixedThreadPool() {
        //1.创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待
        //2.此处线程执行完毕毕后，程序不会终止，因为线程池中的corePoolSize=2(看源码)，所以线程一直存活，程序不会终止
        //3.手动关闭线程池，如果请求比较密集，不用手动关闭，避免频繁创建线程，否则可以手动关闭，减少资源占用
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10).forEach(e -> {
            threadPool.submit(() -> System.out.println("任务被执行,线程：" + Thread.currentThread().getName()));
            threadPool.execute(() -> {
            });
        });
    }
}
