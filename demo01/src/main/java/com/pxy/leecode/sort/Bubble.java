package com.pxy.leecode.sort;

import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description 冒泡排序
 * @date 2021/7/7 18:08
 */
public class Bubble {

    public static void main(String[] args) {
        /**
         * 冒泡排序
         * 相邻比较
         * 每次找出数组中最大或者最小的一个数
         */
        int[] arr = {1, 2, 5, 9, 6, 3};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

}
