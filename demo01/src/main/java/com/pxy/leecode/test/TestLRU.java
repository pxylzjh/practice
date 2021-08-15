package com.pxy.leecode.test;

import java.util.LinkedList;

/**
 * @author puxy
 * @version 1.0
 * @description 会小二 笔试题
 * @date 2021/7/10 15:41
 */
public class TestLRU {

    /**
     * 3.LRU 的实现，时间复杂度是 O(1)
     */

    public static void main(String[] args) {


        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // returns 1
        cache.put(3, 3); // evicts key 2
        cache.get(2); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1
        cache.get(1); // returns -1 (not found)
        cache.get(3); // returns 3
        Object o = cache.get(4);// returns 4
        System.out.println((Integer) o);

    }





}
