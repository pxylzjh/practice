package com.example.distributelock.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2023/2/3 10:26
 */
@Data
//@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    @Value("${redis.pool.maxTotal}")
    private int maxTotal;

    @Value("${redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${redis.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;

    @Value("${redis.pool.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.pool.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${redis.pool.softMinEvictableIdleTimeMillis}")
    private long softMinEvictableIdleTimeMillis;

    @Value("${redis.pool.maxWaitMillis}")
    private long maxWaitMillis;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.pool.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${redis.pool.blockWhenExhausted}")
    private boolean blockWhenExhausted;


    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.dbIndex}")
    private int dbIndex;

    @Value("${redis.usePool}")
    private boolean usePool;


    @Bean
    @Order(1)
    public JedisConnectionFactory jedisConnectionFactory() {

        // 哨兵集群节点
        Set<String> setRedisNode = new HashSet<>();
        setRedisNode.add("121.42.157.181:26379");
        setRedisNode.add("121.42.157.181:26380");
        setRedisNode.add("121.42.157.181:26381");
        // myMaster是哨兵配置文件中的 主节点名,必须保持一致不然报错
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration("myMaster", setRedisNode);

        // 1.配置 jedis 连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(timeBetweenEvictionRunsMillis));
        poolConfig.setMinEvictableIdleTime(Duration.ofMillis(minEvictableIdleTimeMillis));
        poolConfig.setSoftMinEvictableIdleTime(Duration.ofMillis(softMinEvictableIdleTimeMillis));
        poolConfig.setMaxWait(Duration.ofMillis(maxWaitMillis));
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestWhileIdle(testWhileIdle);
        poolConfig.setBlockWhenExhausted(blockWhenExhausted);

        // 2.配置 jedis 客户端
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(poolConfig)
                .and()
                .readTimeout(Duration.ofMillis(timeout))
                .build();

        // 说明：jedisPool 和 jedisClient 都可以不配置，spring-data-redis 会根据 不同的JedisConnectionFactory的构造方法 自动创建默认的配置，
        // 详情可看JedisConnectionFactory源码

        // 3.配置连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, clientConfig);
        return connectionFactory;
    }


    @Bean
    @Order(2)
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(jedisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSerial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jacksonSerial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSerial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerial);
        template.afterPropertiesSet();

        return template;
    }

}
