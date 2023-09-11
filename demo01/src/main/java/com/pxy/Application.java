package com.pxy;

import com.pxy.aop.CJLIBServiceImpl;
import com.pxy.aop.JDKService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/1/25 14:53
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) throws Exception {

        LoggerFactory.getLogger(Application.class);
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        int beanDefinitionCount = context.getBeanFactory().getBeanDefinitionCount();
        String[] registeredScopeNames = context.getBeanFactory().getRegisteredScopeNames();
        int beanDefinitionCount1 = context.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
        System.out.println(beanDefinitionCount1);
        System.out.println(registeredScopeNames.length);



        //cjLib 动态代理 CJLIBServiceImpl 没有实现接口
        CJLIBServiceImpl cjlibService = (CJLIBServiceImpl) context.getBean("cJLIBServiceImpl");

        Integer cjLibProxy = cjlibService.test(1);
        //jdk 动态代理
        JDKService service = context.getBean(JDKService.class);
        Integer test = service.test(1);

    }
}
