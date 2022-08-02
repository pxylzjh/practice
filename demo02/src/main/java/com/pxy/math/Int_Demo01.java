package com.pxy.math;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/5/14 16:41
 */
public class Int_Demo01 {

    /**
     * 当int到达临界值时的计算
     * @param args
     */
    public static void main(String[] args) {
        int x = -2147483648;
        System.out.println(-x);
        System.out.println(x-8);

        int y = 2147483647;
        System.out.println(y+1);
        System.out.println(y+8);
    }
}
