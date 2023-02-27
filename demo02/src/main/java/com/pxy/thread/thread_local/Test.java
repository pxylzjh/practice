package com.pxy.thread.thread_local;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author puxy
 * @version 1.0
 * @description 为什么 {@link java.lang.ThreadLocal.ThreadLocalMap.Entry} 的 value 不设置为 弱引用
 * @date 2023/2/15 00:54
 */
public class Test {

    /**
     * 下列代码论证：为什么{@link java.lang.ThreadLocal.ThreadLocalMap.Entry} 的 value 不设置为弱引用
     *
     *  首先，从源码可知，Entry继承 {@link WeakReference}，并且调用了 WeakReference的构造方法，将key变成了弱引用，也就是当前ThreadLocal变成了弱引用
     *
     *  那么，假设我们把value也通过调用 WeakReference的构造方法，将其变为弱引用会如何呢？
     *
     *  下面我们将用HashMap来代替ThreadLocalMap，将HashMap的key和value都变成弱引用，手动执行gc，看最终打印结果是什么
     *
     *   注意：弱引用在发生GC就会被回收，前提是在后续的代码中没有直接引用
     */

    public static void main(String[] args) throws Exception {
        // 用 HashMap 替代 ThreadLocalMap,并且将 key 和 value 都转为 弱引用
        Map<WeakReference<Integer>, WeakReference<Integer>> map = new HashMap<>(8);
        WeakReference<Integer> key = new WeakReference<>(666);
        WeakReference<Integer> value = new WeakReference<>(777);
        map.put(key,value);
        System.out.println("put success");
        // 手动执行gc
        System.gc();
        Thread.sleep(1000);
        // 输出
        System.out.println("key："+ key +"\nvalue："+ map.get(key).get());
    }

}
