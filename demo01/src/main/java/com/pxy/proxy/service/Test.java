package com.pxy.proxy.service;

import java.lang.reflect.Proxy;

/**
 * @author puxy
 * @version 1.0
 * @description 测试动态代理
 * @date 2021/4/2 14:58
 */
public class Test {
    public static void main(String[] args) {
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[]{Hello.class}, // 传入要实现的接口
                new HelloHandler()); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }
}
