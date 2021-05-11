package com.pxy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/3/27 14:54
 */
public class Test {

    public static void main(String[] args) {
        /**
         * currentTimeMillis 以毫秒为单位 起始时间为 1970.01.01 00:00:00 UTC时间(世界统一时间基于原子时钟,比GMT格林尼治时间更准,因为GMT基于地球自转)
         * nanoTime 以纳秒为单位 没有固定起始时间 是基于cpu的时钟周期来计时的
         */
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());

    }

    private static void work1() {
        //好像是计算字符串中 不重复的 子串 的长度
        String str = "abcdcabccabcc";
        int max = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            ArrayList<Character> chars = new ArrayList<>();
            chars.add(str.charAt(i));
            int x = 0;
            for (int j = i + 1; j < str.length() - 1; j++) {
                boolean contains = false;
                for (int i1 = 0; i1 < chars.size(); i1++) {
                    if (chars.get(i1) == str.charAt(j)) {
                        x = chars.size();
                        contains = true;
                        break;
                    }
                }
                if (contains) {
                    break;
                } else {
                    chars.add(str.charAt(j));
                }
            }
            max = max < x ? x : max;
        }
        System.out.println(max);
    }

    private static void work(int x, int arr[]) {
        Integer basket[] = new Integer[1024];
        for (int i = 0; i < arr.length; i++) {
            basket[arr[i]] = arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                continue;
            }
            int sub = x - arr[i];
            if (basket[sub] != null) {
                int res = basket[sub];
                basket[sub] = null;
                basket[arr[i]] = null;
                System.out.println(res + "+" + arr[i] + "=" + x);
            }

        }
    }


}
