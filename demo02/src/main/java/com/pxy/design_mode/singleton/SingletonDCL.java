package com.pxy.design_mode.singleton;

/**
 * @author puxy
 * @version 1.0
 * @description 双检锁 单例设计模式 - double check lock
 * @date 2022/8/1 16:14
 */
public class SingletonDCL {

    /**
     * 线程安全的 双检锁 单例模式 需要给 instance 实例对象加上 volatile关键字来 禁止指令重排序
     * 因为对象的初始化并不是原子操作,比如 给实例分配内存、调用构造方法初始化对象、将instance对象指向初始化好的内存空间, // TODO: 2022/8/1 对象的初始化过程再说,感觉不太对
     * 所以为了提高效率jvm可能会对指令进行重排序,就会出现对象尚未初始化就被返回了,也就是instance=null
     *
     * 解决办法就是给instance加上volatile禁止 创建对象时进行 指令重排序
     */
    private static volatile SingletonDCL instance = null;

    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class){
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }

}
