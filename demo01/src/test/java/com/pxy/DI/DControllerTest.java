package com.pxy.DI;

import com.pxy.DI.DController;
import com.pxy.test.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author puxy
 * @version 1.0
 * @description test
 * @date 2022/9/15 00:50
 */
public class DControllerTest extends BaseTest {

    @Resource
    private DController dController;

    @Test
    void testSetInjection() {
        dController.testSetInjection();
    }
}