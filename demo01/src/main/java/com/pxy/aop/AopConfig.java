package com.pxy.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2023/6/7 04:51
 */
@Aspect
@Component
public class AopConfig {

    @Pointcut("execution(* com.pxy.aop.*.*(..))")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public void around() {
        System.out.println("aop");
    }

}
