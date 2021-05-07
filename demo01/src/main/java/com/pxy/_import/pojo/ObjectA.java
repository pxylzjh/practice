package com.pxy._import.pojo;

/**
 * @author puxy
 * @version 1.0
 * @description 测试@Import注解
 * @date 2021/1/25 14:24
 */
public class ObjectA {

    public void printName (){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("类名："+ Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
