package leecode.test;

/**
 * @author puxy
 * @version 1.0
 * @description 会小二 笔试题
 * @date 2021/7/10 15:01
 */
public class Test01 {


    public static void main(String[] args) {
        int[] arr = {-3, 8, 9, 10, 1, 2, 3, 4, 5, 7, -1};
        findMaxDiff(arr);
    }

    private static void findMaxDiff(int[] arr) {
        //最小值
        int min = arr[0];
        //最小值下标
        int minIndex = 0;
        //最大值
        int max = arr[0];
        //最大值下标
        int maxIndex = 0;

        int result = 0;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }

            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }

        }
        result = max - min;
        System.out.println("最大差值为：" + result);
    }
}
