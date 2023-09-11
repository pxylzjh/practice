package com.pxy.aop;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2023/6/7 04:53
 */

@Service("cJLIBServiceImpl")
public class CJLIBServiceImpl{

    public Integer test(Integer i) {
        return i;
    }

}
