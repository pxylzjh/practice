package com.pxy.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author puxy
 * @version 1.0
 * @description 测试Spring的3种注入方式
 * @date 2022/9/15 00:43
 */
@Controller
public class DController {

    private DService dService;

    @Autowired
    public void setDService(DService dService){
        this.dService = dService;
    }

    // set方式注入
    public void testSetInjection(){
        dService.testSetInjection();
    }


}
