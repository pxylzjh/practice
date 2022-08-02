package com.pxy.lambda;

/**
 * @author puxy
 * @version 1.0
 * @description 学习Lambda
 * @date 2021/1/15 16:35
 */
public class TestLambda1 extends FunctionInterface{


    public static void main(String[] args) {

        /**
         * 语法形式为 ()->{}, ()描述参数列表, {}描述具体方法体, -> 为lambda运算符, 读作goes to
         * 注意：此列子是自己重写了lambda表达式接口的方法
         */
        NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("无返回无参数");
        };
        noReturnNoParam.method();

        NoReturnOneParam noReturnOneParam = (int a) ->{
            System.out.println("一个参数无返回");
        };
        noReturnOneParam.method(4);

        NoReturnMultiParam noReturnMultiParam = (int a,int b)->{
            System.out.println("多参数无返回");
        };
        noReturnMultiParam.method(4,5);

        ReturnNoParam returnNoParam = ()->{
            System.out.println("无参数有返回");
            return 0;
        };
        int method2 = returnNoParam.method();

        ReturnOneParam returnOneParam = (int e)->{
            System.out.println("一个参数有返回");
            return e;
        };
        int method1 = returnOneParam.method(1);

        ReturnMultiParam returnMultiParam = (int a,int b)->{
            System.out.println("多个参数有返回");
            return a+b;
        };
        int method = returnMultiParam.method(1, 2);

    }


}
