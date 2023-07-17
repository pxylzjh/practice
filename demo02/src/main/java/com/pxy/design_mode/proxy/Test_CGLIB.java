package com.pxy.design_mode.proxy;

import com.pxy.design_mode.proxy.service.Hello;
import com.pxy.design_mode.proxy.service.HelloImpl;
import com.pxy.design_mode.proxy.service.HelloServ;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author puxy
 * @version 1.0
 * @description 测试 CGLIB 动态代理
 * @date 2023/6/7 02:26
 */
public class Test_CGLIB {

    /**
     * 关于动态代理的两种实现JDK 和 CGLIB
     * 都可以对 接口 以及 实现类进行 动态代理
     *
     * 区别：
     * JDK 要求 被代理类必须 实现接口 或者 本身就是个接口
     * CGLIB 可以对 接口、接口实现类、类 进行代理
     *
     *
     */


    public static void main(String[] args) {
        // 对有接口的类进行代理
        test01();

        // 对没接口的类进行代理
//        test02();
        // 对接口进行代理
//        test03();


    }

    private static void test03() {
        Enhancer enhancer = new Enhancer();

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                System.out.println("没接口的类-前置增强");
                // 被代理类是接口 并没有方法可调用
                System.out.println("没接口的类-后置增强");
                return null;
            }
        });
        enhancer.setSuperclass(Hello.class);

        // 创建代理类
        Hello proxy = (Hello)enhancer.create();
        // 由于 被代理类 是接口，并没有具体实现，所以被代理方法并不会执行
        proxy.morning("没接口的类-cglib");
    }

    private static void test02() {
        Enhancer enhancer = new Enhancer();

        HelloServ target = new HelloServ();

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                System.out.println("没接口的类-前置增强");
                methodProxy.invoke(target, objects);
                System.out.println("没接口的类-后置增强");
                return null;
            }
        });
        enhancer.setSuperclass(HelloServ.class);

        HelloServ proxy = (HelloServ)enhancer.create();
        proxy.morning("没接口的类-cglib");
    }

    private static void test01() {
        HelloImpl target = new HelloImpl();

        Enhancer enhancer = new Enhancer();

        // 设置 目标类 class
        enhancer.setSuperclass(HelloImpl.class);

        // 设置回调对象
        enhancer.setCallback(new HelloMethodInterceptor(target));

        // 创建代理对象
        HelloImpl proxy = (HelloImpl)enhancer.create();

        // 通过代理对象调用方法
        String res = proxy.morning("cglib");
        System.out.println(res);
    }


}
