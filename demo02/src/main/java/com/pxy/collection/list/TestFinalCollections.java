package com.pxy.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author puxy
 * @version 1.0
 * @description 不可变集合
 * @date 2023/1/11 08:47
 */
public class TestFinalCollections {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Integer[] arr = {1,2,3,4,5,6,7};
        List<Integer> asList = Arrays.asList(arr);
        asList.add(9);
        System.out.println(asList);
        list.equals("");

        List<Integer> integers = list.subList(1, 2);
        // 可变集合 -> 不可变
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);


    }

}
