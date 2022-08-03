package leecode.day1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author puxy
 * @version 1.0
 * @description https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @date 2022/4/7 16:48
 */
public class T3 {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */

    public static void main(String[] args) {
        String str = "abba";

        int max = solution(str);
        System.out.println(max);
    }

    private static int solution(String s) {
        int start = 0;
        int max = 0;
        int len = s.length();
        // 用hash表记录,key为当前字符,value为对应下标
        Map<Object, Integer> map = new HashMap();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 发现重复的字符
                // 1.如果start>map.get(c)时，start不变，出现这种情况例子：abba，先发现b重复，start移动到下标2，然后头又发现a重复，此时start=2而map.get(c)=0，所以start不变
                // 2.如果start<=map.get(c)时，将start设置为 map 中所存下标 + 1
                // start = start > map.get(c) + 1 ? start : map.get(c) + 1;
                start = Math.max(start, map.get(c) + 1);
            }
            // max = max > i - start + 1 ? max : i - start + 1;
            max = Math.max(max, i - start + 1);
            map.put(c, i);
        }
        return max;
    }

}
