package com.pxy.lambda.builder;

/**
 * @author puxy
 * @version 1.0
 * @description 测试构造器
 * @date 2022/11/24 01:27
 */
public class TestBuilder {

    public static void main(String[] args) {

        Builder<Cat> builder = Builder.builder(Cat::new);

        Cat cat = builder.with((cat1, age) -> cat1.setAge(age), 10)
                .with(Cat::setColor, "red")
                .build();

        Cat cat2 = Builder.builder(Cat::new)
                .with(Cat::setName, "tom")
                .with(Cat::setColor, "red")
                .build();

        System.out.println(cat.getAge());


        TestBuilder test = new TestBuilder();

        test.t((c, s) -> c.getName().concat(s));

    }


    public void t(TestBuilder.TwoParam func) {

    }

    @FunctionalInterface
    public interface TwoParam {
        void method(Cat s, String c);
    }
}

