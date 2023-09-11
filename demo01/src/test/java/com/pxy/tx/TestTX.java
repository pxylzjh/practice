package com.pxy.tx;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxy.test.BaseTest;
import com.pxy.tx.entity.TestEntity;
import com.pxy.tx.mapper.TestMapper;
import com.pxy.tx.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author puxy
 * @version 1.0
 * @description TestTX
 * @date 2023/8/2 16:42
 */
public class TestTX extends BaseTest {
    @Autowired
    private TestMapper testMapper;


    @Test
    public void test(){

        List<TestEntity> testEntities = testMapper.selectList(new LambdaQueryWrapper<TestEntity>().eq(TestEntity::getId, 100));
        testEntities.forEach(System.out::println);

    }

    @Test
    public void test01(){

        List<TestEntity> testEntities = testMapper.selectList(new QueryWrapper<TestEntity>());
        testEntities.forEach(System.out::println);

    }


    @Test
    public void test02() {

        int insert = testMapper.insert(new TestEntity() {{
            setId(111);
            setName(222);
        }});

    }
    @Autowired
    private TestService testTransaction;

    @Test
    public void test03() {

        Integer integer = testTransaction.testInsert();
        System.out.println(integer);

    }
}
