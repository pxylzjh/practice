package leecode.sort;


import java.util.Arrays;

/**
 * @author puxy
 * @version 1.0
 * @description 堆排序
 * @date 2021/10/14 15:10
 */
public class HeapSort {

    /**
     * 堆排序：
     * 由堆的结构可知,堆的根节点就是数组中的最大值,那么把0位置的数移除heapSize--,再进行heapify操作,顶点又会是数组的最大值,
     * 循环操作直到heapSize=0,数组也就排完序了
     * <p>
     * 时间复杂度
     * heapInsert操作时间复杂度
     * 假设往堆里添加N个元素,那么堆的高度为logN,每添加一个元素最多要进行logN次操作,所以时间复杂度O(N*longN)
     * <p>
     * heapify操作时间复杂度
     * 每次移除的都是顶点,所以每次heapify最多进行logN-1次操作,一共进行N次,所以时间复杂度也是 O(N*logN)
     * <p>
     * 所以整体时间复杂度为 O(N*logN)
     * (常数省略)
     * <p>
     * 空间复杂度
     * 因为只是在原数组的基础上进行交换,所以空间复杂度 O(1)
     */

    public static void main(String[] args) {

        int arr[] = {3, 6, 4, 1, 7, 9, 8, 2, 0};
//        heapSort(arr);
        heapSort2(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 优化后的
     *
     * @param arr
     */
    private static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //形成堆
        /**
         * 形成堆的时候不采用一个一个元素添加的方式,而是采用从上往下heapify的方式,而最后一层不用进行操作且最后一层的元素个数为N/2
         * 倒数第二层有N/4个元素。。等比数列,所以时间复杂度 O(N),相比遍历数组进行 heapInsert操作快了不少
         */
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        //将顶点和最后一个元素交换,heapSize减一
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {//O(N)
            //向下构建堆
            heapify(arr, 0, heapSize);//O(logN)
            //构建好之后在交换顶点和最后一个元素
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //形成堆
        for (int i = 0; i < arr.length; i++) {//O(N)
            heapInsert(arr, i);//O(logN)
        }
        int heapSize = arr.length;
        //将顶点和最后一个元素交换,heapSize减一
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {//O(N)
            //向下构建堆
            heapify(arr, 0, heapSize);//O(logN)
            //构建好之后在交换顶点和最后一个元素
            swap(arr, 0, --heapSize);
        }
    }


    private static void heapify(int arr[], int index, int heapSize) {
        //左子树的下标
        int left = 2 * index + 1;
        while (left < heapSize) {//是否有子树
            //从子树中选出最大的
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;//left+1<heapSize是否有右子树
            //将最大的子树和当前元素比较
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            //交换最大子节点和当前节点
            swap(arr, index, largest);
            //继续往下比较
            index = largest;
            left = 2 * index + 1;
        }


    }


    private static void heapInsert(int arr[], int index) {
        while (arr[index] > arr[(index - 1) / 2]) {//当前节点大于父节点则交换
            swap(arr, index, (index - 1) / 2);
            //继续向上比较
            index = (index - 1) / 2;
        }


    }


    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}