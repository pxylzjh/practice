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
//        int x = 6;
//        int arr[] = {1,2,3,4,5,6,7,1,8,9,9};
//        work(x,arr);

        String str = "abcab1cddd";
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
