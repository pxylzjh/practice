package com.example.distributelock.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @author puxy
 * @version 1.0
 * @description redisson试下分布式锁
 * @date 2023/5/23 04:03
 */
@RestController
@RequiredArgsConstructor
public class RedissonDistributeLock {


    private final RedisTemplate<String, String> redisTemplate;

    private final RedissonClient redissonClient;


    @RequestMapping("/test01")
    public String redissonDistLock() {

        RLock lock = redissonClient.getLock("lockName");
        lock.lock(30, TimeUnit.SECONDS);
        try {
            // 1.查询当前库存
            String stock = (String) redisTemplate.opsForValue().get("stock");
            if (stock != null && stock.length() > 0) {
                int intValue = Integer.parseInt(stock);
                if (intValue > 0) {
                    // 2.扣减库存
                    redisTemplate.opsForValue().set("stock", String.valueOf(--intValue));
                }
            }

        } finally {
            lock.unlock();
        }


        return "success";
    }

}
