package com.pxy._volatile;

/**
 * @author puxy
 * @version 1.0
 * @description 测试缓存行对齐
 * @date 2021/1/8 16:09
 */
public class TestVolatile {

    /**
     * Long 为 8 字节，在计算机中 1 字节(byte)=8比特(bit)，所以1个Long占64bit
     * 现在主流CPU缓存行大小为64字节，8个Long类型正好占满一个缓存行
     */
    public static class F{
        public long p0,p1,p2,p3,p4,p5,p6,p7;
    }
    public static class T extends F{
        public volatile long x =0;
    }
    public static T[] arr1 =new T[2];
    static {
        arr1[0] = new T();
        arr1[1] = new T();
    }
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<9999999;i++) {
                    arr1[0].x=i;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<9999999;i++) {
                    arr1[1].x=i;
                }
            }
        });
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime()-start)/100_0000);
    }
}
