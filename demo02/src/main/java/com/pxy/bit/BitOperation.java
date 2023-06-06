package com.pxy.bit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 位运算
 * @date 2022/8/2 10:53
 */
public class BitOperation {


    public static void main(String[] args) {

        int aa = 4;
        int bb = 6;

        System.out.println((aa&bb) + "sss" + (bb&aa));

        int f = 6;
        System.out.println(f >>>1);

        System.out.println(1 ^ 0);



        int size = 64;
        int n = size - (size >>> 3);
        System.out.println(size >>> 2);

        int s = 1;
        s <<= 2;
        System.out.println(s);

//        test();

        System.out.println(Integer.MAX_VALUE+1);


        int a = 4;
        int x = (a >>> 2);
        int r = a - (a >>> 2);

        System.out.println(x);
        System.out.println(r);

    }
    public static void test() {
        int sshift = 0;
        int ssize = 1;
        while (ssize < 16) {
            ++sshift;
            ssize <<= 1;
        }
        int c = 16 / ssize;
        if (c * ssize < 16)
            ++c;
        int cap = 2;
        while (cap < c)
            cap <<= 1;

        System.out.println(sshift+"-"+ssize+"-"+c+"-"+cap);
    }

}
