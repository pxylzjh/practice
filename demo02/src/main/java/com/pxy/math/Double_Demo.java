package com.pxy.math;

import java.math.BigDecimal;

/**
 * @author puxy
 * @version 1.0
 * @description 测试double精度丢失问题
 * @date 2021/5/14 9:47
 */
public class Double_Demo {

    public static void main(String[] args) {

//        method_02();
//        method_01();
        double a = 1.1;
        double b = 1.2;
        BigDecimal ba = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal bab = new BigDecimal(a + b);
        System.out.println(ba+"--"+bb);
        System.out.println(bab);
        System.out.println(a+b);

    }

    private static void method_02() {
        //浮点型整数部分超过1000w时，转成String会变成科学计数法
        double a = 10000000.2223;
        System.out.println(a);
    }

    /**
     * Double的精度问题
     */
    private static void method_01() {
        // 0.1+0.2=0.3?? ==> 0.30000000000000004
        double a = 0.1;
        double b = 0.2;
        System.out.println(a+b);

        //float计算的时候正确输出了 0.3 ，猜测是它精度不够
        float c = 0.1f;
        float d = 0.2f;
        float r = c+d;
        System.out.println(r);
        BigDecimal ds = new BigDecimal("1234111111111111111111111111111111.56");
        BigDecimal df = new BigDecimal(0.1f);
        System.out.println(df);
        BigDecimal dd = new BigDecimal(0.1);
        System.out.println(dd);

        BigDecimal str_01 = new BigDecimal("0.1");
        System.out.println(str_01);
        BigDecimal str_0100 = new BigDecimal("0.100");
        System.out.println(str_0100);
        System.out.println(str_01.equals(str_0100));
        int compareTo = str_01.compareTo(str_0100);
        System.out.println(compareTo);
    }
}
