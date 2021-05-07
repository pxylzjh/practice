package com.pxy.string_tokenizer;

import java.util.StringTokenizer;

/**
 * @author puxy
 * @version 1.0
 * @description 学习StringTokenizer
 * @date 2021/4/29 17:12
 */
public class TestStringTokenizer {


    /**
     * 用于字符串分隔
     *
     */

    public static void main(String[] args) {
        //以"!()#" 分隔,且分割后的元素不包含分隔符
        StringTokenizer stk = new StringTokenizer("!pr!od#we!q(452)","!()#",false);
        //分隔后的元素个数
        int countTokens = stk.countTokens();
        System.out.println(countTokens);
        //循环输出分隔后的元素
        while (stk.hasMoreTokens()) {
            System.out.println(stk.nextToken());
        }
    }




}
