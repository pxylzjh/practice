package com.pxy.cycle_dependence;

import com.pxy.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author puxy
 * @version 1.0
 * @description 测试循环依赖
 * @date 2023/2/27 14:52
 */
public class CycleDependenceTest extends BaseTest {

    @Autowired
    private CycleDependence cycleDependence;

    @Test
    public void testCycle() {
        cycleDependence.test();
    }
}