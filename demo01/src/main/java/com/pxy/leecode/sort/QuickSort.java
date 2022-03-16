package com.pxy.leecode.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * @author puxy
 * @version 1.0
 * @description 快排
 * @date 2021/10/11 22:06
 */

public class QuickSort {

    /**
     * 快排其实和"荷兰国旗问题"{@link com.pxy.leecode.NetherLands}相类似
     * 快排1.0：
     * 选数组的最后一个数作为基准,将小于等于该数的放到左边,大于该数的放到右边,那么执行完毕后就能确定该数的最终位置,
     * 同理再将小于和大于区域进行相同操作,每次都能确定一个数的位置
     * <p>
     * 每次确定一个数位置的操作就叫做 partition 分区操作
     * <p>
     * 快排2.0：
     * 和1.0的区别就是增加了“等于”区域,就是说每一次操作能确定多个数的位置,就能减少操作的次数
     * <p>
     * 快排3.0：
     * 1.0和2.0每次都是将固定位置的数作为基准(最后一位),这样很容易人为制造极端情况,导致快排的时间复杂度为O(N^2)
     * 所以在此基础上引入随机位置的方式,避免极端情况的出现
     *
     * 快排的空间复杂度,1.0和2.0最坏情况下 为 O(N) 因为需要 递归N次
     * 3.0 空间复杂度O(logN) ,因为每次都取近似中点的位置进行划分
     */
    public static void main(String[] args) {


        int arr[] = {1, 2, 6, 4, 9, 7, 5, 3, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }


    public static void quickSort(int arr[], int L, int R) {

        if (L < R) {//表示 递归到 两两数 相比较
            //选一个随机位置的数与最后一个数交换
            swap(arr, R, L + (int) (Math.random() * (R - L + 1)));//注意：> 区域的L不等于0,所以需要+L才是正确下标
            //分区
            int[] p = partition(arr, L, R);
            //分完区之后就能确定<num 和 >num 的区域,并进行递归了
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);

        }

    }

    /**
     * 以arr[R]作划分
     * 返回 等于区域的 开始下标 和 结束下标
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int[] partition(int[] arr, int L, int R) {
        int less = L - 1; // <区域 边界
        int more = R; // >区域 边界
        while (L < more) {// L表示当前数的位置, arr[R] 表示划分值
            if (arr[L] < arr[R]) {// 当前数 < 划分值
                //交换当前数和 小于区域的下一个数 并且 <区域 边界+1,指针L +1
                swap(arr, L++, ++less);
            } else if (arr[L] > arr[R]) {// 当前数 > 划分值
                //交换当前数和 大于边界的 前一个数, >区域 边界-1,指针L不变
                swap(arr, L, --more);
            } else {// 等于 不做操作 指针 后移
                L++;
            }
        }
        //注意：要将最后一位 与 > 区域 的第一位 交换位置
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int R, int index) {
        int temp = arr[R];
        arr[R] = arr[index];
        arr[index] = temp;
    }


}