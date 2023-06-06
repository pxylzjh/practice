package com.pxy.cycle_dependence.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author puxy
 * @version 1.0
 * @description A
 * @date 2023/2/27 14:48
 */
@Component
public class A {
    @Autowired
    private B b;
}
