package com.pxy.design_mode.proxy;

import com.pxy.design_mode.proxy.service.Hello;
import com.pxy.design_mode.proxy.service.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author puxy
 * @version 1.0
 * @description 测试JDK动态代理
 * @date 2021/4/2 14:58
 */
public class Test_JDK {

    /**
     * JDK动态代理的原理是通过反射生成一个被代理接口的匿名类，所以要求必须有接口
     */

    public static void main(String[] args) {
        test01();

        // 测试只有接口没有实现类 的情况下生成代理对象
//        test02();

    }

    /**
     * 测试只有接口没有实现类 的情况下生成代理对象
     * 可知：JDK动态代理就算只有接口 没有实现类也能创建代理对象，其实细想，对接口进行代理和对实现类进行代理 本质没有区别，Mybatis就是对Mapper接口进行的动态代理
     */
    private static void test02() {
        Hello proxy = (Hello)Proxy.newProxyInstance(
                HelloImpl.class.getClassLoader(),
                new Class[]{HelloImpl.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("对hello接口进行代理");
                return null;
            }
        });
        proxy.morning("xx");
    }

    private static void test01() {
        // 目标对象
        HelloImpl target = new HelloImpl();

        // 代理对象
        Hello proxy = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[]{Hello.class}, // 传入要实现的接口
                new HelloInvocationHandler(target)); // 传入处理调用方法的InvocationHandler
        String bob = proxy.morning("Bob");
        System.out.println(bob);
    }
}
