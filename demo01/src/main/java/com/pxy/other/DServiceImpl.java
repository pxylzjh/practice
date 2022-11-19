package com.pxy.other;

import org.springframework.stereotype.Service;

/**
 * @author puxy
 * @version 1.0
 * @description 测试Spring的3种注入方式
 * @date 2022/9/15 00:42
 */
@Service
public class DServiceImpl implements DService{
    @Override
    public void testSetInjection() {
        System.out.println("set方法注入");
    }
}
