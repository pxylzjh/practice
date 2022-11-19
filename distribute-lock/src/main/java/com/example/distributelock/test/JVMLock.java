package com.example.distributelock.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author puxy
 * @version 1.0
 * @description 基于java的锁实现库存扣减
 * @date 2022/11/9 00:18
 */
@RestController
@Slf4j
public class JVMLock {

    // 库存数量
    private static int stock = 5000;

    private ReentrantLock lock = new ReentrantLock();

    @RequestMapping("/reentrantLock")
    public String testReentrantLock(){

        lock.lock();
        try {
            if (stock>0) {
                stock--;
                log.info("-----------------{}", stock);
            } else {
                log.info("sales out");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return "success";
    }


    // synchronized 加锁
    @RequestMapping("/synchronized")
    public String testSynchronized () throws Exception {

        synchronized (JVMLock.class) {
            if (stock > 0) {
                stock--;
            } else {
                throw new Exception("扣减库存失败");
            }
            log.info("--------------------{}",stock);
        }

        return "success";
    }




}
