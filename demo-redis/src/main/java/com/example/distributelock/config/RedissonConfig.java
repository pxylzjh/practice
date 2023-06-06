package com.example.distributelock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puxy
 * @version 1.0
 * @description redisson 配置类
 * @date 2023/5/23 03:15
 */

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {

        // 单机
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        /**
         // 其它配置
        // 主从
        config.useMasterSlaveServers().setMasterAddress("").addSlaveAddress("","","");
        // 哨兵
        config.useSentinelServers().setMasterName("") // 指定主节点名
                .addSentinelAddress("",""); // 设置哨兵节点

        // 集群
        config.useClusterServers().setScanInterval(2000) //redisson客户端定时扫描集群状态
                .addNodeAddress("","");
        */
        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }


}
