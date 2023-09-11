package com.pxy.anotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puxy
 * @version 1.0
 * @description 测试下Configuration和  @Component的区别
 * @date 2023/7/19 15:07
 */
public class TestConfiguration {

    /**
     * @Configuration注解与@Component的区别主要在于
     * @Configuration 可以保证@Bean方法相互调用情况下不会重复创建对象，即保证了单例
     *
     * @Component、@Service、@Repository、@Controller等注解无法做到
     */

    @Bean
    public Object getObj1() {
        System.out.println("new obj1");
        return new Object();
    }


    @Bean
    public Object getObj2() {
        System.out.println("new obj2");

        // 此方法中调用了 3 次 getObj1 会创建几次 obj1 呢
        Object obj1 = getObj1();
        obj1 = getObj1();
        obj1 = getObj1();
        return new Object();
    }
}
