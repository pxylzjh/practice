package com.pxy.tx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author puxy
 * @version 1.0
 * @description 测试实体类
 * @date 2023/8/2 16:37
 */
@TableName("test")
@Data
public class TestEntity {

    @TableId
    private Integer Id;


    private Integer name;


}
