package com.pxy.collection.map;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 测试concurrentHashMap
 * @date 2023/1/31 16:21
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        hashMap.containsKey("");
        hashMap.get("");

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("null", "null");
        concurrentHashMap.containsKey("1");
        concurrentHashMap.get("");
        concurrentHashMap.size();

        int size = 16;
        int n = size - (size >>> 2);
        System.out.println(n);

        int s = 1;
        s <<= 2;
        System.out.println(s);

        System.out.println(Integer.MAX_VALUE+1);

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
