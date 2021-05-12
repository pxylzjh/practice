package com.pxy._import.config;

import com.pxy._import.pojo.ObjectA;
import com.pxy._import.registrar.MyRegistrar;
import com.pxy._import.selector.MyImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author puxy
 * @version 1.0
 * @description @Import方式注入对象到Spring容器
 * @date 2021/1/25 14:35
 */
@Import({ObjectA.class, MyImportSelector.class, MyRegistrar.class})
@Configuration
public class ImportConfig {
    /**
     * 使用@Configuration是为了让spring扫描到这个类，并且直接通过@Import引入这ObjectA类
     */
}
