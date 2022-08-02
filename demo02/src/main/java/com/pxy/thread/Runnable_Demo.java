package com.pxy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author puxy
 * @version 1.0
 * @description Runnable 创建线程
 * @date 2021/5/12 11:34
 */
public class Runnable_Demo {


    public static void main(String[] args) {

//        direct_run();
        thread_start();
//        Thread thread = new Thread();
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        threadPool.execute(thread);
    }

    /**
     * 要开启线程，必须要调用Thread类的start方法，所有的多线程代码都必须通过Thread类的start方法来运行
     * Thread其实也是个Runnable的实现类
     *
     * 但是还是推荐使用Runnable来创建线程，原因：
     * 1.java单继承多实现，如果继承thread来创建多线程，就不能再继承其他类了
     * 2.Runnable是个函数式接口，可以使用Lambda表达式来创建线程
     * 3.更容易与线程池等高级API配合(没发现啊，它既然是Runnable的实现类，用Runnable作为参数的方法，也可以用Thread啊。。)
     */
    private static void thread_start() {
        IntStream.range(0,10).forEach(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println("当前线程为："+name);
                }
            };
            //通过构造方法将runnable实现类传入Thread类赋值给private Runnable target;属性，源码可知该属性是将要被具体运行的对象
            Thread thread = new Thread(runnable);
            thread.start();
        });
    }

    /**
     * 直接调用runnable的run方法，发现并没有开启新线程，而是使用的主线程main
     */
    private static void direct_run() {
        IntStream.range(0,10).forEach(e->{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println("当前线程为："+name);
                }
            };
            runnable.run();
        });
    }
}
