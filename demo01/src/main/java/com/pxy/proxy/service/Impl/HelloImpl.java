package com.pxy.proxy.service.Impl;

import com.pxy.proxy.service.Hello;

/**
 * @author puxy
 * @version 1.0
 * @description 实现类
 * @date 2021/4/2 14:56
 */
public class HelloImpl implements Hello {
    @Override
    public void morning(String msg) {
        System.out.println("good morning:"+msg);
    }
}
