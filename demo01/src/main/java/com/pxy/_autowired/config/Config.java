package com.pxy._autowired.config;

import com.pxy._autowired.service.impl.AService1;
import com.pxy._autowired.service.IAService;
import org.springframework.context.annotation.Bean;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2023/1/13 09:41
 */
//@Configuration
public class Config {

    @Bean
    public IAService aService(){

        return new AService1();
    }

}
