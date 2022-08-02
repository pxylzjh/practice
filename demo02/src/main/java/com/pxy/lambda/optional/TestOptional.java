package com.pxy.lambda.optional;


import lombok.Data;

import java.util.Collections;
import java.util.Optional;

/**
 * @author puxy
 * @version 1.0
 * @description 字符串相关的操作
 * @date 2022/3/21 12:57
 */
public class TestOptional {

    public static void main(String[] args) {
        Bird bird = new Bird();
        boolean present = Optional.of(bird).isPresent();
        System.out.println(present);
        bird = null;
        // 使用 Optional.ofNullable 处理 为 null 的情况
        String aNull = Optional.ofNullable(bird).map(Bird::getCategory).orElse("is null");
        System.out.println(aNull);

        // 使用 Optional.ofNullable 优雅的处理null
        String color = Optional.ofNullable(bird).map(e -> e.getAttr()).map(e -> e.color).orElse("no color");

        // 传统写法
        if (bird != null) {
            Attr attr = bird.getAttr();
            if (attr != null) {
                if (attr.getColor() != null) {
                    System.out.println(attr.getColor());
                } else {
                    System.out.println("no color");
                }
            }
        }

    }

    @Data
    private static class Bird {

        private String category;

        private String name;

        private Attr attr;
    }

    @Data
    private static class Attr {

        private String color;

        private String size;

    }
}
