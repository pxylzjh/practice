package leecode;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author puxy
 * @version 1.0
 * @description 位运算 实现两数值交换
 * @date 2022/3/16 16:37
 */
public class BitOperate {


    public static void main(String[] args) {

        // 交换两个数的值
        // 异或运算：不进位加法
        int a = 1;// 0000 0001
        int b = 2;// 0000 0010

        a = a ^ b;  // 0000 0001 ^ 0000 0010 = 0000 0011
        b = a ^ b;  // 0000 0011 ^ 0000 0010 = 0000 0001 = 1 -> b=1
        a = a ^ b;  // 0000 0011 ^ 0000 0001 = 0000 0010 = 2 -> a=2

        System.out.println("a=" + a + " b=" + b);

        Short s = null;
//        System.out.println(s == 1);
        HashMap<String, String> map = new HashMap<>();
        map.put("", "");
        map.entrySet();
        map.get("");

        ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<>();
        cMap.put("1", "1");
        cMap.get("1");
        cMap.size();

        int x = 8;

        int y = 15;

        System.out.println((x - 1) & y);
        System.out.println(y % x);

    }

}
