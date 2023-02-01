package com.pxy.thread.unsafe;

import sun.misc.Unsafe;

/**
 * @author puxy
 * @version 1.0
 * @description unsafe类实现cas
 * @date 2023/1/13 23:59
 */
public class CasDemo {

    private volatile int state = 0;

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    private static final long offset;

    static {
        try {
            offset = UNSAFE.objectFieldOffset(CasDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomething() {
        // 返回true 说明成功获取锁
        if(UNSAFE.compareAndSwapInt(this, offset, 0, 1)){
            // do something
        }
    }
}
