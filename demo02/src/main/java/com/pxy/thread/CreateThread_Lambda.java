package com.pxy.thread;

import com.pxy.lambda.FunctionInterface;

/**
 * @author puxy
 * @version 1.0
 * @description 创建 线程 的 一种写法
 * @date 2021/2/24 11:01
 */
public class CreateThread_Lambda {
    public static void main(String[] args) {
        CreateThread_Lambda ts = new CreateThread_Lambda();
        /**
         * m方法必须是一个无参无返回的方法，就像 runnable 接口的run方法，runnable 接口只有一个 run方法切是无参无返回的，
         * 所以此处传入ts的m方法就相当于run方法的实现了，有点抽象，要一个这样的方法，就传入一个这样的方法
         */
        new Thread(ts::m,"ts").start();

        ts.test1(ts::m1);

        ts.test2(ts::m2);
    }

    public void m(){
        System.out.println("do something");
    }

    public void m1(){
        System.out.println("do something");
    }

    public void m2(int str){
        System.out.println("do something");
    }

    //需要一个无参无返回的方法
    public void test1(FunctionInterface.NoReturnNoParam f){
        f.method();
    }

    //需要一个有参无返回的方法
    public void test2(FunctionInterface.NoReturnOneParam f){
        f.method(2);
    }
}
