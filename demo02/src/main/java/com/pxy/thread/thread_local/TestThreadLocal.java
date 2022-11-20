package com.pxy.thread.thread_local;


/**
 * @author puxy
 * @version 1.0
 * @description threadLocal 学习
 * @date 2022/11/20 16:59
 */
public class TestThreadLocal {

    /**
     *
     * ThreadLocal:线程变量
     *
     *
     *
     */
    private static final ThreadLocal T = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return super.initialValue();
        }
    };

    public static void main(String[] args) {




    }


}
