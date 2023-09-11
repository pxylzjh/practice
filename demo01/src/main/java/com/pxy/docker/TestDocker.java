package com.pxy.docker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author puxy
 * @version 1.0
 * @description 测试idea部署docker容器
 * @date 2023/7/20 16:59
 */

@RestController
@Slf4j
public class TestDocker {

    @GetMapping("/hello")
    public String test(String req) {
        log.info("----------received {}", req);
        return req;
    }
}
