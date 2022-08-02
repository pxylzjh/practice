package com.pxy.lambda;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author puxy
 * @version 1.0
 * @description 测试jdk提供的函数式接口
 * @date 2021/2/1 16:42
 */
public class TestLambda6 {

    public static void main(String[] args) {

        /**
         * @FunctionalInterface
         * public interface Function<T, R>
         */
        Function<Integer,Integer> function = a -> a+1;

        Function function1 = Function.identity();
        Object apply = function1.apply(1);


        String str = "";
        boolean empty = str.isEmpty();
        Predicate predicate = e -> false;





    }


}
