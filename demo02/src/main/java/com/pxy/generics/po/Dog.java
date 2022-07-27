package com.pxy.generics.po;

/**
 * @author puxy
 * @version 1.0
 * @description Dog
 * @date 2022/7/27 18:46
 */
public class Dog extends Animal{
    @Override
    void move() {
        System.out.println("run");
    }
}
