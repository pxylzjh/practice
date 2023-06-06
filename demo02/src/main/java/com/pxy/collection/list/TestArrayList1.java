package com.pxy.collection.list;

import java.util.ArrayList;

/**
 * @author puxy
 * @version 1.0
 * @description TestArrayList
 * @date 2023/2/28 16:23
 */
public class TestArrayList1 {

    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>(Integer.MAX_VALUE);
        for (int i = 0; i < 10; i++) {
            objects.add((byte) 1);
        }



        objects.add((byte) 2);
        objects.add((byte) 3);
        objects.add((byte) 4);

    }
}
