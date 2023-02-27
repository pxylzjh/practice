package com.pxy.thread.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author puxy
 * @version 1.0
 * @description ThreadLocal 导致内存泄漏 案例
 * @date 2023/2/24 17:50
 */
public class Test2 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        InitAdd initAdd = new InitAdd();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                Integer before = initAdd.threadLocal.get();
                initAdd.add();
                Integer after = initAdd.threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "\t" + before + "\t" + after);
                System.out.println();
                //移除ThreadLocal中的自定义变量
                //initAdd.threadLocal.remove();
            });

        }
    }
}
class InitAdd{

    ThreadLocal<Integer> threadLocal=ThreadLocal.withInitial(()->0);

    public void add(){
        threadLocal.set(1+threadLocal.get());
    }
}