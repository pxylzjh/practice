package com.pxy.interview;

/**
 * @author puxy
 * @version 1.0
 * @description 一些面试题
 * @date 2021/2/4 11:23
 */
public class Demo01 {

    /**
     * 不使用额外的空间,完成两个整数的交换
     */
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        a= a+b;
        b= a-b;
        a= a-b;

        System.out.println("a= "+a+";b= "+b);

        int c = 1;int d = 2;
        c = c^d;
        System.out.println(c);

        /**
         * 位运算
         * 左移 <<:
         * 右移 >>:
         */
        int num = 45;//101101
        int x = num >> 1;//010110
        System.out.println(x);
        int x1 = num << 1;
        System.out.println(x1);
        //1011010
        //00010
    }


}
