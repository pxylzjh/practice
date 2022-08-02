package com.pxy.lambda;

/**
 * @author puxy
 * @version 1.0
 * @description Lambda表达式简化写法
 * @date 2021/1/15 17:05
 */
public class TestLambda2 extends FunctionInterface{

    public static void main(String[] args) {

        /**
         * 语法形式为 ()->{}, ()描述参数列表, {}描述具体方法体, -> 为lambda运算符, 读作goes to
         * 注意：此列子是自己重写了lambda表达式接口的方法
         */
        //省略参数类型,可以不写参数类型,但是必须所有参数都不写
        NoReturnMultiParam noReturnMultiParam = (a, b) -> {
            System.out.println("多参数无返回");
        };
        noReturnMultiParam.method(4, 5);

        //如果方法体只有一条语句,可以省略大括号
        NoReturnNoParam noReturnNoParam = () -> System.out.println("无返回无参数");
        noReturnNoParam.method();

        //只有一个参数可以省略参数的小括号
        NoReturnOneParam noReturnOneParam = a -> System.out.println("一个参数无返回");
        noReturnOneParam.method(4);

        //如果方法体只有一条语句且为return,可以省略方法体大括号
        ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("多个参数有返回");
            return a + b;
        };
        ReturnMultiParam returnMultiParam1 = (a,b)->a+b;
        int method = returnMultiParam.method(1, 2);
    }

}
