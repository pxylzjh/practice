package com.pxy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author puxy
 * @version 1.0
 * @description Callable学习
 * @date 2021/5/12 10:47
 */
public class Callable_Demo {


    public static void main(String[] args) throws Exception {
//        call_futureTask();
        call_threadPool();


    }

    /**
     * 线程池方式通过callable开启线程，其实底层也是使用了FutureTask进行包装
     * 有两种方式执行线程submit和invokeAll
     * 其中submit方法单次执行，不会调用FutureTask的get方法
     * invokeAll传入的是List<Callable>，且会调用get方法，所以是阻塞的
     */
    private static void call_threadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, 1, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        List<Callable<Integer>> callables = new ArrayList<>();
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        IntStream.range(0,10).forEach(e->{
            Callable<Integer> callable = new Callable() {
                @Override
                public Integer call() throws Exception {
                    String name = Thread.currentThread().getName();
                    System.out.println("当前线程为："+name);
                    return 1;
                }
            };
            Future<Integer> future = threadPool.submit(callable);
            futures.add(future);
        });
        futures.forEach(e-> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });
        //invokeAll，传入Callable的集合，返回future的集合，会阻塞，因为它调用了get方法
//        List<Future<Integer>> futures = threadPool.invokeAll(callables);
    }

    /**
     * 使用Callable创建线程
     * 需要使用FutureTask进行包装，FutureTask类实现了RunnableFuture接口，该接口同时继承了Runnable和Future接口
     * 再将FutureTask对象通过构造函数传入Thread，并赋值给target属性，它是个Runnable类型对象，此时就变成了和Runnable接口创建线程一样了
     * 然后我们可以在FutureTask类的run方法中看到，它调用了Callable的call方法
     * 并且抓了所有异常，然后set到了FutureTask的outcome属性，如果没有异常，将call方法的返回值set到outcome
     * 在调用FutureTask的get方法获取线程执行结果的时候，将outcome返回了
     */
    private static void call_futureTask() {
        IntStream.range(0,10).forEach(e->{
            //1.创建callable实现类
            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                    return 1;
                }
            };
            //2.创建FutureTask对象
            FutureTask<Integer> futureTask = new FutureTask(callable);
            //3.创建Thread对象开启线程
            Thread thread = new Thread(futureTask);
            thread.start();
            try {
                //通过FutureTask获取线程的返回值，该方法是阻塞的，直到所有线程都执行完
                Integer integer = futureTask.get();
                System.out.println(integer);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });
    }


}
