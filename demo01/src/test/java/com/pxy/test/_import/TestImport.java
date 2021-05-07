package com.pxy.test._import;

import com.pxy._import.pojo.ObjectA;
import com.pxy._import.pojo.ObjectB;
import com.pxy._import.pojo.ObjectC;
import com.pxy.test.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/1/25 15:07
 */
@DisplayName("测试@Import导入配置的三种方式")
public class TestImport extends BaseTest {

    @Autowired
    private ObjectA objectA;

    @Autowired
    private ObjectB objectB;

    @Autowired
    private ObjectC objectC;

    @Test
    @DisplayName("测试引入普通类")
    public void test01() {
        objectA.printName();
    }

    @Test
    @DisplayName("测试通过ImportSelector方式引入")
    public void test02() {
        objectB.printName();
    }

    @Test
    @DisplayName("测试通过ImportBeanDefinitionRegistrar方式引入")
    public void test03() {
        objectC.printName();
    }

}
