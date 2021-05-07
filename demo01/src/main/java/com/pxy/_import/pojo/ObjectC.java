package com.pxy._import.pojo;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/1/25 15:38
 */
public class ObjectC {

    public void printName(){
        System.out.println("类名："+ Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
