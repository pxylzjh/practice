package com.pxy.class_loader;

import java.util.HashMap;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/7/7 11:45
 */
public class Test01 {

    public static void main(String[] args) {

        //能不能自己写一个限定名为java.lang.String的类,并在程序中调用它

        /**
         * 不能,因为我们无法加载它
         * 1.采用jvm自带的3种classLoader加载由于双亲委派的机制,最终会委派给bootstrap classloader加载,并且加载的是rt.jar的string
         * 2.采用自定义类加载器去加载会报错,在classLoader的 defineClass方法中校验了包路径,
         *
         *
         *
         */
    }

}
