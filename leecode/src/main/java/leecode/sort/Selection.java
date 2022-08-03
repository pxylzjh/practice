package leecode.sort;

import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description 选择排序
 * @date 2021/7/7 18:20
 */
public class Selection {

    /**
     * 每次找出最小的放在当前下标的位置
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 9, 6, 3};

        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]>arr[j]){
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;

        }
        System.out.println(Arrays.toString(arr));

    }
}
