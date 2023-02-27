package com.pxy.thread.thread_local;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description 阿里TTL实现父子线程数据传递
 * @date 2023/2/27 00:39
 */
public class TTL {


    public static void main(String[] args) throws InterruptedException {


        TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal();
        ExecutorService es = Executors.newFixedThreadPool(1);

        ttl.set("父线程");
        es.execute(TtlRunnable.get(() -> {

            String s = ttl.get();
            System.out.println("父线程修改数据前:" + s);

        }));
        ttl.set("父线程修改了");
        es.execute(TtlRunnable.get(() -> {

            String s = ttl.get();
            System.out.println("父线程修改数据后:" + s);
            ttl.set("我是子线程");

            new Thread(() -> {

                String s1 = ttl.get();
                System.out.println("子线程的 子线程 获取值:" + s1);

            }).start();


        }));

        TimeUnit.SECONDS.sleep(1);
        String s = ttl.get();
        System.out.println("父线程获取子线程修改后的值:" + s);
        es.shutdown();

    }

}
