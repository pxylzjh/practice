package com.pxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/1/25 14:53
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {

        LoggerFactory.getLogger(Application.class);
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        int beanDefinitionCount = context.getBeanFactory().getBeanDefinitionCount();
        String[] registeredScopeNames = context.getBeanFactory().getRegisteredScopeNames();
        int beanDefinitionCount1 = context.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
        System.out.println(beanDefinitionCount1);
        System.out.println(registeredScopeNames.length);

    }
}
