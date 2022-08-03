package leecode;

/**
 * @author puxy
 * @version 1.0
 * @description 递归
 * @date 2021/5/14 18:01
 */
public class Recursion {


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int i = arrSum(arr, 0);
        System.out.println(i);
    }

    /**
     * 递归求数组的和
     * @param arr
     * @param n
     * @return
     */
    private static int arrSum(int[] arr, int n) {
        int len = arr.length;
        if (n == len - 1) {
            return arr[n];
        } else {
            return arr[n]+arrSum(arr,n+1);
        }
    }


}
