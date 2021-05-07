package com.pxy._import.registrar;

import com.pxy._import.pojo.ObjectC;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author puxy
 * @version 1.0
 * @description 通过实现ImportBeanDefinitionRegistrar方式导入的类
 * @date 2021/1/25 15:34
 */
public class MyRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(ObjectC.class);
        registry.registerBeanDefinition("objectC",beanDefinition);
    }
}
