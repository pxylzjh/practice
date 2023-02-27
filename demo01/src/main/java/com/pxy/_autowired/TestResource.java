package com.pxy._autowired;

import com.pxy._autowired.service.IAService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2022/12/27 16:32
 */
@Controller
public class TestResource {


    @Resource
    private IAService AService1;

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ApplicationContext applicationContext;

    public void test(){
        AService1.say();

    }
}
