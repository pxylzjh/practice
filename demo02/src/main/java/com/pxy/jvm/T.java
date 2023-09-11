package com.pxy.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author puxy
 * @version 1.0
 * @description 查看对象各个字段所占大小
 * @date 2023/7/20 15:09
 */
public class T {

    public static void main(String[]args) {
        User o=new User();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}


class User {
    private String id;
    private String name;
    private double sal;
    private Object obj;
}