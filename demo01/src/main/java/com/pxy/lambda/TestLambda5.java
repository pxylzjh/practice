package com.pxy.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author puxy
 * @version 1.0
 * @description lambda操作集合
 * @date 2021/1/17 21:17
 */
public class TestLambda5 {

    public static void main(String[] args) {
        //排序
        ArrayList<A> list = new ArrayList<>();
        list.add(new A("xxx",2));
        list.add(new A("aaa",3));
        list.add(new A("zzz",4));
        list.add(new A("yyy",5));

//        list.sort(new Comparator<A>() {
//            @Override
//            public int compare(A o1, A o2) {
//                return o1.getAge()-o2.getAge();
//            }
//        }.reversed());

//        list.sort((a,b)->a.getAge()-b.getAge());//升序
//        list.sort((a,b)->b.getAge()-a.getAge());//降序
//        System.out.println(list);
        biBao();

    }

    /**
     * lambda表达式闭包问题
     */
    private static void biBao() {
        int num = 2;
        //Consumer是JDK提供的一个函数式接口
        Consumer<Integer> consumer = a -> {
            System.out.println(num);
        };

        Consumer<Integer> andThen = consumer.andThen(a -> {
            System.out.println(1);
        });

        /**
         * 次处不能修改num的值,因为匿名内部类中的变量必须是final的
         */
//        num = 3;
        andThen.accept(3);
    }

    private static void removeList() {
        //删除集合中某个元素
        ArrayList<A> list = new ArrayList<>();
        list.add(new A("xx",2));
        list.add(new A("aa",3));
        list.add(new A("zz",4));
        list.add(new A("yy",5));

        list.removeIf( a->a.getAge()>2);

        System.out.println(list);

        ArrayList<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2,1,2,3,4,5,6,7);
        list2.removeIf(a->a>2);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class A{
    private String name;
    private int age;
}
