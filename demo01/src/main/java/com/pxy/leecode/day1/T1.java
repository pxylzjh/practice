package com.pxy.leecode.day1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author puxy
 * @version 1.0
 * @description https://leetcode-cn.com/problems/two-sum/
 * @date 2022/4/6 16:45
 */
public class T1 {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     */

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));


    }

    // O(n^2)
//    public static int[] twoSum(int[] nums, int target) {
//        int[] res = {0,0};
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (target - nums[i] == nums[j]) {
//                    res[0] = i;
//                    res[1] = j;
//                }
//            }
//        }
//        return res;
//    }

    // O(n)
    public static int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i<nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new RuntimeException("no numbers have bean found");
    }



}
