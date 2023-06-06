package com.pxy.design_mode.proxy;

import com.pxy.design_mode.proxy.service.HelloImpl;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author puxy
 * @version 1.0
 * @description CGLIB的MethodInterceptor类似于JDK的InvocationHandler 用于对目标类进行增强
 * @date 2023/6/7 02:28
 */
public class HelloMethodInterceptor implements MethodInterceptor {

    private HelloImpl target;

    public HelloMethodInterceptor() {
    }

    public HelloMethodInterceptor(HelloImpl target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 对目标方法进行前置操作
        System.out.println("前置增强");
        // 执行 目标方法
        methodProxy.invoke(target, objects);
        // 对目标方法进行后置操作
        System.out.println("后置增强");
        return null;
    }
}
