package com.pxy.cycle_dependence;

import com.pxy.cycle_dependence.bean.A;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author puxy
 * @version 1.0
 * @description
 * @date 2023/2/27 14:47
 */
@Service
@RequiredArgsConstructor
public class CycleDependence {

    private final A a;



    public void test(){
        System.out.println(a);
    }
}
