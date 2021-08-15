package com.pxy.leecode.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author puxy
 * @version 1.0
 * @description 会小二 笔试题
 * @date 2021/7/10 15:16
 */
public class Test2 {

    public static void main(String[] args) {

        /**
         * 2.给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。（要求包含unit，test ，testcase）
         * 例如：输入："()(())"       输出：6
         */

        String str = ")()(())";
        int max = findLongest(str);
        System.out.println(max);
    }

    private static int findLongest(String str) {
        int start = -1;
        int end = 0;
        for (int i = 0; i < str.length(); i++) {
            //先找到第一个 ( 的位置,之后 start 就固定不动了
            if ('(' == str.charAt(i)&&start==-1) {
                start = i;
            }
            //找到最后一个 ) 的位置
            if (')'== str.charAt(i)) {
                end = i;
            }

        }
        //最后输出结果
        int max = end+1-start;
        return max;
    }
}
