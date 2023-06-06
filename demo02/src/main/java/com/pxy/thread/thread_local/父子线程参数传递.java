package com.pxy.thread.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author puxy
 * @version 1.0
 * @description InheritableThreadLocal实现父子线程参数传递
 * @date 2023/2/27 00:00
 */
public class 父子线程参数传递 {

    public static void main(String[] args) {

        // 测试通过 InheritableThreadLocal 获取父线程的数据
        // test01();
        // 父线程修改ThreadLocal的值后
        test02();

    }

    private static void test02() {
        ExecutorService es = Executors.newFixedThreadPool(1);
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        inheritableThreadLocal.set("我是父线程");
        threadLocal.set("xxxx");
        System.out.println(threadLocal.get());
        es.submit(() -> {

            String s = inheritableThreadLocal.get();
            System.out.println("父线程修改ThreadLocal的值之前:" + s);

        });

        inheritableThreadLocal.set("父线程修改了");

        es.submit(() -> {

            String s = inheritableThreadLocal.get();
            Thread.currentThread();
            System.out.println("父线程修改ThreadLocal的值之后:" + s);
        });
    }

    private static void test01() {
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("我是父线程");

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("我是父线程");
        new Thread(() -> {

            String name = Thread.currentThread().getName();
            String data = inheritableThreadLocal.get();
            String data1 = threadLocal.get();
            System.out.println("InheritableThreadLocal:" + data + "\nThreadLocal:" + data1);


        }).start();
    }

}
