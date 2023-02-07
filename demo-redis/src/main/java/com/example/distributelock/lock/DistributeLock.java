package com.example.distributelock.lock;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 分布式锁  及其 演进过程
 * @date 2022/11/7 18:16
 */
@RestController
public class DistributeLock {

    private RedisTemplate<String, String> redisTemplate;

    /**
     * 这里采用 ObjectProvider进行依赖注入，底层也是调用，因为在springboot的自动配置包中看到有很多地方用了这种方式，所以尝试一下
     * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)}
     */
    public DistributeLock(ObjectProvider<RedisTemplate<String, String>> objectProvider) {
        objectProvider.stream().forEach(template -> {
            if (template instanceof StringRedisTemplate) {
                this.redisTemplate = template;
            }
        });

    }

    // 分布式锁 实现可重入  参考ReentrantLock
    @RequestMapping("/reentrantDistributedLock")
    public String reentrantDistributedLock() {

        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        reentrantLock.tryLock();
        try {
            reentrantLock.tryLock(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return "success";
    }



    @RequestMapping("/testRedisDistributeLock")
    public String testRedisDistributeLock() {
        // =========================== 获取分布式锁 ===========================
        // 简单获取锁
//        tryAcquire();
        String uuid = UUID.randomUUID().toString();
        String lockName = "lock";
        // 获取锁并设置过期时间防止死锁，设置UUID防止误删
        tryAcquire1(lockName, uuid, 3000L, TimeUnit.MILLISECONDS);

        // ================================ 扣减库存 ================================
        try {
            // 1. 查询当前库存
            String stock = (String) redisTemplate.opsForValue().get("stock");
            if (stock != null && stock.length() > 0) {
                int intValue = Integer.parseInt(stock);
                // 2. 判断是否>0
                if (intValue > 0) {
                    // 3. 扣减库存
                    redisTemplate.opsForValue().set("stock", String.valueOf(--intValue));
                }
            }
        } finally {
            // 简单释放锁
//            unLock();
            // 判断是否是自己的锁
//            unLock1(lockName, uuid);
            // 原子性判断是否是自己的锁
            unLock2(lockName, uuid);
        }

        return "success";
    }

    // ************************************************* 使用lua脚本保证释放锁的原子性 *************************************************

    /**
     *
     * lua：一种轻量级脚本语言，基于c语言编写，可以灵活的嵌入其它应用程序中(通常是c、c++)
     * redis提供了eval指令对lua脚本的支持
     */
    /**
     *
     * @param lockName 锁名
     * @param uuid     uuid
     */
    public void unLock2(String lockName, String uuid) {
        try {
            String script = "if (redis.call('get', KEYS[1])==ARGV[1]) " +
                            "then " +
                                "return redis.call('del', KEYS[1])" +
                            "else " +
                                "return 0 end";
            // 注意：DefaultRedisScript中定义的resultType类型 要和 我们定义的脚本的类型保持一致，不然会报错
            redisTemplate.execute(new DefaultRedisScript(script, Boolean.class), Arrays.asList(lockName), uuid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("释放锁失败");
        }
    }


    // ************************************************* 防误删的锁 *************************************************
    /**
     * 防误删的锁
     * <p>
     * 设置uuid作为锁的value，释放锁的时候判断uuid是否一致，不一致说明不是自己的锁不能删除
     * <p>
     * 存在的问题：无法自动续期，释放锁操作不是原子操作
     */
    private void tryAcquire1(String lockName, String uuid, long timeout, TimeUnit unit) {
        // 尝试获取锁并设置过期时间3s，防止服务器宕机执行释放锁失败造成死锁
        while (!redisTemplate.opsForValue().setIfAbsent(lockName, uuid, 3000L, TimeUnit.MILLISECONDS)) {
            // 未获取到锁，重试
            try {
                // 这里睡眠50毫秒是为了减少减少竞争，可以提升性能
                // 为什么去掉性能会降低
                // 因为在while循环中会不断的尝试获取锁，不断的请求redis，加上在多线程环境下(jmeter测试 100个线程 50次/s) 对redis的并发量一下就上来了

                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 释放锁的时候判断是否是自己的锁
     * 存在的问题：判断 和 删除 不是原子操作，还是可能会出现误删问题
     * 解决办法：通过lua脚本保证原子性
     *
     * @param lockName
     * @param uuid
     */
    public void unLock1(String lockName, String uuid) {
        if (uuid.equals(redisTemplate.opsForValue().get(lockName))) {
            redisTemplate.delete(lockName);
        }
    }
    // ************************************************* 简单的redis分布式锁 *************************************************
    /**
     * 简单的redis分布式锁
     * <p>
     * 存在的问题：
     * 1.误删：虽然设置了过期时间3s，但是当业务代码执行时间超过3s时，就会出现误删的情况，比如线程A获取锁执行业务代码需要5s，5s后线程A执行del释放锁，但是第3s的时候锁过期了其它线程就能
     * 获取到锁了并且也需要5s来执行业务代码，并且线程B获取到锁的第二秒，锁就会被线程A主动释放掉
     * <p>
     * 2.自动续期：当业务代码执行时间超过锁的过期时间时，需要自动续期防止业务代码还未执行完就被其它线程获取到锁
     */
    private void tryAcquire() {
        // 尝试获取锁并设置过期时间3s，防止服务器宕机执行释放锁失败造成死锁
        while (!redisTemplate.opsForValue().setIfAbsent("lock", "1111", 3000L, TimeUnit.MILLISECONDS)) {
            // 未获取到锁，重试
            try {
                // 这里睡眠50毫秒是为了减少减少竞争，可以提升性能
                // 为什么去掉性能会降低
                // 因为在while循环中会不断的尝试获取锁，不断的请求redis，加上在多线程环境下(jmeter测试 100个线程 50次/s) 对redis的并发量一下就上来了

                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void unLock() {
        redisTemplate.delete("lock");
    }


    @RequestMapping("/set")
    public String setObject() {
        redisTemplate.opsForValue().set("lock", "5000");
        String lock = (String) redisTemplate.opsForValue().get("lock");
        return lock;
    }


}
