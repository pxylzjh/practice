package com.pxy.lambda;

import com.pxy.lambda.pojo.School;
import com.pxy.lambda.pojo.User;

import java.util.function.Consumer;

/**
 * @author puxy
 * @version 1.0
 * @description lambda表达式常用示例-方法引用
 * @date 2021/1/17 20:22
 */
public class TestLambda3 extends FunctionInterface{

    /**
     * 有时候我们不是必须要自己重写某个匿名内部类的方法,我们可以用lambda表达式的接口指向一个已经被实现的方法
     * 要求：1.参数的数量与类型必须与接口定义的一致
     *      2.返回值类型必须与接口定义的一致
     * 语法：方法归属者::方法名    (静态方法归属者为类名,普通方法为对象)
     */

    public static void main(String[] args) {

        //自己重写匿名内部类的方法
        ReturnOneParam lambda1 = a -> add(a);
        System.out.println(lambda1.method(2));

        //引用已经实现的静态方法
        ReturnOneParam lambda2 =  TestLambda3::add;
        System.out.println(lambda2.method(2));

        //引用已经实现的普通方法
        TestLambda3 TL = new TestLambda3();
        ReturnOneParam lambda3 =  TL::less;
        System.out.println(lambda3.method(2));

    }

    private static int add(int a){
        return a+2;
    }

    private int less(int a){
        return a-2;
    }

}
