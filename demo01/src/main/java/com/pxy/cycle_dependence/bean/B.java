package com.pxy.cycle_dependence.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author puxy
 * @version 1.0
 * @description B
 * @date 2023/2/27 14:48
 */
@Component
@RequiredArgsConstructor
public class B {

    private final A a;
}
