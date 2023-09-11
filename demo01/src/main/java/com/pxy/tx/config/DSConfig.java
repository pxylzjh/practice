package com.pxy.tx.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author puxy
 * @version 1.0
 * @description 数据源及事务配置
 * @date 2023/8/1 20:10
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@MapperScan("com.pxy.tx")// 开启事务的注解，可以省略，因为SpringBoot自动装配 在 transactionAutoConfiguration类中使用了该注解，所以不加也能开启事务
public class DSConfig {


    // 1. dataSource
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    // 2. 事务管理器  spring boot 已经自动装配了



    // 3. orm 配置mybatis 而mybatis主要是基于sqlSession来操作数据库的 这里也可以省略，MP-boot-starter提供了自动装配
    //@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {

        return null;
    }






}
