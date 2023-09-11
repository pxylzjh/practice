package com.pxy.aop;

import org.springframework.stereotype.Service;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2023/6/7 04:54
 */
@Service
public class JDKServiceImpl implements JDKService {

    @Override
    public Integer test(Integer i) {
        return i;
    }
}
