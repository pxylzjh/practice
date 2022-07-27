package com.pxy.generics.po;

import lombok.Data;

/**
 * @author puxy
 * @version 1.0
 * @description Bird
 * @date 2022/7/27 18:42
 */
@Data
public class Bird extends Animal{

    @Override
    public void move(){
        System.out.printf("fly");
    }
}
