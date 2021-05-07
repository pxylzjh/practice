package com.pxy._import.pojo;

/**
 * @author puxy
 * @version 1.0
 * @description ImportSelector测试实体类
 * @date 2021/1/25 15:23
 */
public class ObjectB {
    public void printName (){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("类名："+ Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
