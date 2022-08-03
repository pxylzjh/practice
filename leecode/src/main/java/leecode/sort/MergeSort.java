package leecode.sort;

import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description 归并排序-顾名思义通过递归实现的排序
 * @date 2021/10/12 15:34
 */
public class MergeSort {


    public static void main(String[] args) {

        /**
         * 归并排序思路
         * 将数组分为2部分,先使两部分分别有序,再将两部分合并
         * 合并思路
         * 创建一个辅助数组,遍历两部分,比较大小,将小的放入辅助数组,并将指针向右移,大的部分指针不动,直到一边的指针溢出,
         * 再将剩下的一边的数依次放入辅助数组,最后将辅助数组拷贝到原数组中
         *
         * 计算时间复杂度
         * master公式
         * T(N) = a*(N/b) + O(N^d)
         * 前半部分表示递归操作的时间复杂度,a表示进行了几次递归,b表示每次递归操作占总量的占比
         * 后半部分表示除了递归以外的时间复杂度
         * 3种情况
         * 1.log(b,a)<d --> O(N^d)
         * 2.log(b,a)>d --> O(N^log(b,a))
         * 3.log(b,a)==d -->O(N^d*logN)
         *
         * 递归部分可知 a=2 b=2,合并部分时间复杂度为 排序阶段时间复杂度O(N) 复制到原数组 O(N)所以除了递归过程外时间复杂度为O(N) 所以d=1
         * 所以满足第3种情况,时间复杂度为 O(N*logN)
         *
         * 空间复杂度 O(N) 主要包括2部分 递归 和 辅助数组 ,递归 占用的空间数 logN,辅助数组 N,总共为 logN+N,所以空间复杂度为 O(N)
         *
         */
        int arr[] = {1, 3, 5, 7, 2, 9, 4, 8, 6};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);//计算中点
        mergeSort(arr, L, mid);//左侧有序
        mergeSort(arr, mid + 1, R);//右侧有序
        //合并
        merge(arr, L, R, mid);

    }

    private static void merge(int[] arr, int L, int R, int M) {
        int temp[] = new int[R - L + 1];//注意
        int i = 0;
        //指针
        int P1 = L;//注意：需要将L 和 M赋值给新变量,不能修改 L 和 M,会影响其它递归子过程
        int P2 = M +1;
        while (P1 <= M && R >= P2) {//没越界
            //依次比较放入辅助数组
            System.out.println(L +"---"+ R+"--"+M);
//            if (arr[P1]> arr[P2]){
//                temp[i]= arr[P2];
//                P2++;
//            } else {
//                temp[i]= arr[P1];
//                P1++;
//            }
//            i++;
            temp[i++] = arr[P1]<=arr[P2]?arr[P1++]:arr[P2++];//注意：上面可以这样简写
        }
        //越界
        while (P1 <= M){
//            temp[i]= arr[P1];
//            i++;
//            P1++;
            temp[i++]=arr[P1++];
        }
        while(P2<= R){
//            temp[i]= arr[P2];
//            i++;
//            P2++;
            temp[i++]=arr[P2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[i+L]=temp[i];//注意:不能写成 arr[i]=temp[i],因为在子递归过程中arr不是从0开始的,而是L开始,到R结束
        }
    }


}