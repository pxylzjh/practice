package leecode.test;

import sun.misc.Lock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/7/13 20:12
 */
public class TE {


    public static void main(String[] args) {

        int[] arrayInt=new int[]{1,2,3,4,5,6,7,8,9,10};
        String[] arrayString=new String[]{"A","B","C","D","E","F","G","H","I","J"};

        ReentrantLock lock1 = new ReentrantLock();

        Lock lock = new Lock();
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
//        Thread t1 = new Thread(() -> {
//
//            Thread t2 = new Thread(() -> {
//
//                synchronized ("a"){
//                    for (int i = 0; i < arrayString.length; i++) {
//                        System.out.println(arrayString[i]);
//                        try {
//                            Thread.currentThread().wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }, "t2");
//            t2.start();
//
//
//            for (int i = 0; i < arrayInt.length; i++) {
//                System.out.println(arrayInt[i]);
//                t2.notify();
//            }
//
//        }, "t1");
//
//        t1.start();



    }
}
