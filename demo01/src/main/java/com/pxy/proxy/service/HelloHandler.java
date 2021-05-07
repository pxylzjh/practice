package com.pxy.proxy.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/4/2 14:35
 */
public class HelloHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        if (method.getName().equals("morning")) {
            System.out.println("Good morning, " + args[0]);
        }
        return null;
    }

}
