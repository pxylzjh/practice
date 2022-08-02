package com.pxy.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author puxy
 * @version 1.0
 * @description 学习IntStream
 * @date 2021/1/14 16:18
 */
public class TestIntStream {
    public static void main(String[] args) {

        /**
         * ()->1 其实是 generate 需要传入一个 IntSupplier 接口的实现类,返回值是个 int
         * generate() 产生一个无限的流
         */
        int sum = IntStream.generate(() -> 1).limit(10).sum();
        System.out.println(sum);

        /**
         * range(start,end)产生一个区间内有序流(左闭右开),不包含最后一个元素
         */
        int sum1 = IntStream.range(10, 20).sum();
        System.out.println(sum1);

        /**
         * rangeClosed(start,end)产生一个区间内有序流(左右都闭合),包含最后一个元素
         */
        int sum2 = IntStream.rangeClosed(1, 10).sum();
        System.out.println(sum2);

        /**
         * of()使用值快速创建流
         */
        IntStream.of(1, 2, 3, 4).forEach(System.out::println);

        /**
         * empty() 创建一个空的流
         */
        IntStream empty = IntStream.empty();

        /**
         * 构造者模式创建流
         */
        IntStream.builder().add(1).add(2).add(3).build().forEach(System.out::print);

        /**
         * 创建一个有序的无限流,需要传入初始值,对元素操作的函数
         */
        IntStream.iterate(1, e -> add(e)).limit(10).forEach(System.out::println);

        /**
         * 合并两个流
         * count()统计元素个数
         */
        IntStream is1 = IntStream.of(1, 2, 3);
        IntStream is2 = IntStream.of(5, 6, 7);
        long count = IntStream.concat(is1, is2).count();
        System.out.println(count);

        /**
         * 过滤不满足条件的元素
         * 过滤不满足 >8 的元素,即 只留下 >8 的元素
         */
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 12).filter(e -> e > 8).forEach(System.out::print);

        /**
         * 转换操作
         * 将每个元素加2
         */
        IntStream.of(1, 2, 3).map(e -> e + 2).forEach(System.out::println);

        /**
         * 转换为对象
         * 转换为String对象
         * 两种写法
         */
        IntStream.of(2, 3, 4).mapToObj(String::valueOf).map(Object::getClass).forEach(System.out::println);
        IntStream.of(2, 3, 4).mapToObj(String::valueOf).map(e -> {return e.getClass();}).forEach(System.out::println);

        /**
         * 转换为数组
         */
        int[] ints = IntStream.of(1, 2, 3, 4).toArray();

        /**
         * 转换为LongStream 也可转为 DoubleStream
         */
        LongStream longStream = IntStream.of(1, 2, 3, 4).asLongStream();

        /**
         * 拍扁操作,其实flatMap就是将Function转换后的stream合并成一个stream
         * IntStream.rangeClosed(0,e)每次都会返回一个 IntStream
         */
        IntStream.of(1, 2, 3, 4).flatMap(e -> IntStream.rangeClosed(0, e)).forEach(System.out::println);

        /**
         * flatMap操作集合
         */
        testFlatMap();


        /**
         * 去重
         */
        long count1 = IntStream.of(1, 2, 3, 4, 4).distinct().count();
        System.out.println(count1);


        /**
         * 排序
         */
        IntStream.of(2, 1, 4, 5, 8, 3).sorted().forEach(System.out::print);

        /**
         * peek 查看元素,主要用于调试一个stream
         */
        IntStream.of(1, 3, 4, 5, 6, 7).filter(e -> e > 3).peek(e -> System.out.println(e)).mapToObj(String::valueOf).forEach(System.out::print);

        /**
         * 跳过操作
         * 跳过前3个元素,从第4个元素开始输出
         */
        IntStream.range(0,10).skip(3).forEach(System.out::println);

        /**
         * reduce 规约操作(不知道啥意思)
         */
        int reduce = IntStream.range(0, 10).reduce(0, (v1, v2) -> v1 + v2);
        System.out.println(reduce);

        IntStream.range(0,10).reduce((v1, v2) -> v1 + v2).ifPresent(System.out::println);

        /**
         * 收集操作
         * collect()自定义逻辑,需要提供 容器工厂、容器收集器、容器组合器
         */
        ArrayList<Object> collect = IntStream.range(0, 10).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        //等价于
        /**
         * boxed()将元素装箱,转换为对象类型
         * 使用Collectors必须是对象类型
         */
        List<Integer> collect1 = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        /**
         * 匹配操作
         */
        boolean b = IntStream.range(0, 10).anyMatch(e -> e > 2);

        boolean b1 = IntStream.range(0, 10).allMatch(e -> e == 2);

        boolean b2 = IntStream.range(0, 10).noneMatch(e -> e == 2);

        /**
         * 查询操作
         */
        int asInt = IntStream.range(3, 10).findFirst().getAsInt();

        int asInt1 = IntStream.range(2, 8).findAny().getAsInt();


    }

    /**
     * flatMap对集合进行并集、交集、差集操作
     */
    private static void testFlatMap() {
        List<A> list1 = new ArrayList<>();
        A a1 = new A() {{ setName("a1");setAge(1);setDesc("aa1");}};
        A a2 = new A() {{ setName("a2");setAge(2);setDesc("aa2");}};
        A a3 = new A() {{ setName("a3");setAge(3);setDesc("aa3");}};
        list1.add(a1);
        list1.add(a2);
        list1.add(a3);

        List<A> list2 = new ArrayList<>();
        A a4 = new A() {{ setName("a4");setAge(4);setDesc("aa4");}};
        A a5 = new A() {{ setName("a5");setAge(5);setDesc("aa5");}};
        list2.add(a1);
        list2.add(a4);
        list2.add(a5);

        /**
         * 交集
         */
        List<A> Intersection = list1.stream().filter(list2::contains).collect(Collectors.toList());

        /**
         * 并集
         */
        List<A> union = Stream.of(list1, list2).flatMap(Collection::stream).distinct().collect(Collectors.toList());

        /**
         * 差集
         */
        List<A> Subtraction = list1.stream().filter(e -> !list2.contains(e)).collect(Collectors.toList());


        /**
         * 合并集合
         */
        Map<Integer, ListContainer> map = new HashMap<>();
        map.put(1,new ListContainer(list1));
        map.put(2,new ListContainer(list2));

        List<A> merge = map.values().stream().flatMap(listContainer -> listContainer.getList().stream()).collect(Collectors.toList());
        System.out.println(merge);

        /**
         * 将集合的元素变为大写
         */
        List<String> collect = list1.stream().map(e -> e.getName().toUpperCase(Locale.ROOT)).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static int add(int i) {
        return ++i;
    }

    @Data
    public static class A {
        private String name;
        private String desc;
        private int age;
    }

    @Data
    @AllArgsConstructor
    public static class ListContainer{
        private List<A> list;
    }

}
