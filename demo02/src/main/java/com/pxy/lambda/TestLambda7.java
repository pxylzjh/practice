package com.pxy.lambda;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/2/5 17:42
 */
public class TestLambda7 {
    /**
     * lambda表达式创建线程
     */

    public static void main(String[] args) {

        //匿名内部类方式
        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                //do something
                System.out.print("");
            }
        }, "thread-01");

        //lambda表达式
        Thread thread02 = new Thread(() -> System.out.print(""), "thread-02");

        Runnable runnable = ()->System.out.print("");
    }
}
