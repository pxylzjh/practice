package com.pxy.leecode;

/**
 * @author puxy
 * @version 1.0
 * @description 位运算 实现两数值交换
 * @date 2022/3/16 16:37
 */
public class BitOperate {


    public static void main(String[] args) {

        // 交换两个数的值
        // 异或运算：不进位加法
        int a = 1;// 0000 0001
        int b = 2;// 0000 0010

        a = a^b;  // 0000 0011 ^ 0000 0010
        b = a^b;  // 0000 0001 = 1
        a = a^b;  // 0000 0011 ^ 0000 0001 = 0000 0010 = 2

        System.out.println("a="+a+" b="+b);

        Short s = null;
        System.out.println(s == 1);
    }

}
