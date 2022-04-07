package com.other;


import lombok.Data;

import java.util.Collections;
import java.util.Optional;

/**
 * @author puxy
 * @version 1.0
 * @description 字符串相关的操作
 * @date 2022/3/21 12:57
 */
public class Test {

    public static void main(String[] args) {
        Bird bird = new Bird();
        boolean present = Optional.of(bird).isPresent();
        System.out.println(present);
        bird = null;
        // 使用 Optional.ofNullable 处理 为 null 的情况
        String aNull = Optional.ofNullable(bird).map(Bird::getCategory).orElse("is null");
        System.out.println(aNull);

    }

    @Data
    private static class Bird {

        private String category;

    }

}
