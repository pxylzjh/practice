package com.example.distributelock;

import com.example.distributelock.lock.DistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DistributeLockApplicationTests {

    @Autowired
    private DistributeLock distributeLock;

    @Test
    void contextLoads() {

        String s = distributeLock.testRedisDistributeLock();
        log.info(s);

    }

}
