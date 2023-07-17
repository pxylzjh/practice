package com.pxy.design_mode.proxy;

import com.pxy.design_mode.proxy.service.Hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author puxy
 * @version 1.0
 * @description HelloHandler
 * @date 2021/4/2 14:35
 */
public class HelloInvocationHandler implements InvocationHandler {

    private Hello target;

    public HelloInvocationHandler() {
    }

    public HelloInvocationHandler(Hello target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        Object res = null;
        if (method.getName().equals("morning")) {
            System.out.println("前置操作");
            res = method.invoke(target, args);
            System.out.println("后置操作");
        }
        return res;
    }

}
