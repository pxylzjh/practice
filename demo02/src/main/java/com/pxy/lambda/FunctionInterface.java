package com.pxy.lambda;

/**
 * @author puxy
 * @version 1.0
 * @description 函数式接口
 * @date 2021/1/17 20:28
 */
public class FunctionInterface {
    /**
     * 多参数无返回值
     **/
    @FunctionalInterface
    public interface NoReturnMultiParam {
        void method(int a, int b);
    }

    /**
     * 无参数无返回值
     **/
    @FunctionalInterface
    public interface NoReturnNoParam {
        void method();
    }

    /**
     * 一个参数无返回值
     **/
    @FunctionalInterface
    public interface NoReturnOneParam {
        void method(int a);
    }

    /**
     * 多个参数有返回值
     **/
    @FunctionalInterface
    public interface ReturnMultiParam {
        int method(int a, int b);
    }


    /**
     * 无参数有返回值
     **/
    @FunctionalInterface
    public interface ReturnNoParam {
        int method();
    }

    /**
     * 一个参数有返回值
     **/
    @FunctionalInterface
    public interface ReturnOneParam {
        int method(int a);
    }

}
