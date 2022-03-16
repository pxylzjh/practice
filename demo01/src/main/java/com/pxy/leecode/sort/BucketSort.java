package com.pxy.leecode.sort;

import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description 桶排序
 * @date 2021/10/14 19:35
 */
public class BucketSort {

    /**
     * 桶排序时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public static void main(String[] args) {
        int arr[] = {17, 35, 23, 13, 14, 67, 123, 4, 42};
        int bits = maxBits(arr);//最大数有多少位
        bucketSort(arr, bits);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 排序过程
     * @param arr
     * @param bits
     */
    private static void bucketSort(int[] arr, int bits) {
        int bucket[] = new int[arr.length];//辅助数组
        int i = 0;
        int digit = 0;//某一位的数值
        //低位->高位  个...百...
        for (int d = 1; d <= bits; d++) {
            int count[] = new int[10];//基数数组,用于统计arr中 ?位<=n的有多少个，例如 个位<=2的有多少个
            //取出元素指定位上的数值,并以其为下标将bucket中对应位置的元素+1
            for (i = 0; i < arr.length; i++) {
                count[getDigit(arr[i], d)] += 1;
            }
            //bucket数组元素进行累加
            for (i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            //逆序遍历原数组
            for (i = arr.length - 1; i >= 0; i--) {
                //根据arr[j]在bucket中的位置放入help中
                digit = getDigit(arr[i], d);
                bucket[count[digit] - 1] = arr[i];
                count[digit]--;
            }
            //将bucket覆盖到arr中
            for (i = 0; i < bucket.length; i++) {
                arr[i] = bucket[i];
            }
        }
    }

    /**
     * 获取指定位上的数
     *
     * @param num
     * @param d
     * @return
     */
    private static int getDigit(int num, int d) {
        return (int) (num % Math.pow(10, d) / Math.pow(10, d - 1));
    }

    /**
     * 计算最大数有多少位
     *
     * @param arr
     * @return
     */
    private static int maxBits(int[] arr) {
        int bits = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        while (max != 0) {
            bits++;
            max /= 10;
        }
        return bits;
    }


}