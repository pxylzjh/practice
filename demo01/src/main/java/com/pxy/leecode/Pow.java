package com.pxy.leecode;

import java.math.BigDecimal;

/**
 * @author puxy
 * @version 1.0
 * @description 练习算法
 * @date 2021/5/14 14:27
 */
public class Pow {

    /**
     * 题目：计算数值的整数次幂，实现 pow(x,n) 即 x^n
     */

    public static void main(String[] args) {
//        double v = myPow(2.0, -2147483648);
//        BigDecimal decimal = new BigDecimal(v);
//        System.out.println(decimal);
    }

    /**
     * 普通递归，需要 n 次计算
     *
     * @param x
     * @param n
     * @return
     */
    private static int pow(int x, int n) {
        /**
         * 2^8 = ((2^2)^2)^2
         *
         * 2^9 = (((2^2)^2)^2)*2
         */
        System.out.println(n);
        int res = 0;

        if (n == 0) {
            return 1;
        } else {
            return x * pow(x, n - 1);
        }
    }

    /**
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        //当 n = 0 时，返回 1
        System.out.println(n);
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            // 当 n < 0 时，返回 1/ x^-n
            return 1 / (x * myPow(x, n - 1));
        } else if (n % 2 == 1) {
            //为避免越界，提取 x
            System.out.println(x);
            return x * myPow(x, n - 1);
        } else {
            return myPow(x * x, n / 2);
        }
    }

}
