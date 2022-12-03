package com.pxy.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author puxy
 * @version 1.0
 * @description 测试下强引用、软引用、弱引用、虚引用
 * @date 2021/7/5 18:01
 */
public class Test {

    //java执行GC判断对象是否存活有两种方式：引用计数 和 可达性 算法
    /**
     * 测试下强引用、软引用、弱引用、虚引用
     */
    public static void main(String[] args) {
        /**
         * 强引用:jvm宁愿抛出OOM也不会回收强引用的对象
         */
        Object obj = new Object();

        /**
         * 软引用:如果内存够,不会回收,不够就会回收它
         */
        SoftReference<Object> soft = new SoftReference<Object>(obj);

        /**
         * 弱引用:垃圾回收器一旦发现了弱引用,不管内存是否够,都会回收
         */
        WeakReference weak = new WeakReference(obj);
        //弱引用转强引用
        Object o = weak.get();

        /**
         * 虚引用:也是不管内存是否够,都会回收,和弱引用不同的是,它必须和引用队列联合使用,在被垃圾回收前,会把这个虚引用加入到与之关联的引用队列中
         */
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<Object>(obj,referenceQueue);


    }
}
