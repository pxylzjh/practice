package com.pxy.tx.service;

import com.pxy.tx.mapper.TestMapper;
import com.pxy.tx.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puxy
 * @version 1.0
 * @description TestTransaction
 * @date 2023/8/1 20:09
 */
@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    @Transactional(rollbackFor = Throwable.class)
    public Integer testInsert() {

        int res = testMapper.insert(new TestEntity() {{
            setId(123);
            setName(123);
        }});
        int i = 1/0;
        return res;
    }

}
