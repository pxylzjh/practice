package com.pxy._autowired.service.impl;

import com.pxy._autowired.service.IAService;
import org.springframework.stereotype.Service;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2022/12/27 16:34
 */
@Service("aService")

public class AService1 implements IAService {
    @Override
    public void say() {
        System.out.println("service A");
    }
}
