package com.pxy.thread;

/**
 * @author puxy
 * @version 1.0
 * @description 获取CPU个数
 * @date 2023/2/1 22:48
 */
public class CpuNumb {

    // 在设置 线程数 的时候，我们往往会根据 当前计算任务的类型 结合 cpu 个数来进行设置
    // cpu个数 又可分为：物理个数 和 逻辑个数
    // 如果需要实现 系统自动 设置合适的线程数，就需要通过代码来获取当前系统的 cpu逻辑个数

    public static void main(String[] args) {

        // 获取cpu逻辑个数
        int processNum = Runtime.getRuntime().availableProcessors();//获取Processors
        System.out.println("逻辑个数:" + processNum);


    }


}
