package com.pxy.test._autowired;

import com.pxy._autowired.TestAutowired;
import com.pxy._autowired.TestResource;
import com.pxy.test.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2022/12/27 16:56
 */
public class TestA extends BaseTest {

    @Resource
    private TestAutowired testAutowired;

    @Resource
    private TestResource testResource;


    @Test
    public void testAutowired(){

        testAutowired.test();
    }

    @Test
    public void testResource(){
        testResource.test();
    }
}
