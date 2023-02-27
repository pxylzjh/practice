package com.pxy._autowired;

import com.pxy._autowired.service.IAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2022/12/27 16:32
 */
@Controller
public class TestAutowired {


    @Autowired
    private IAService AService1;

    public void test(){
        AService1.say();
    }
}
