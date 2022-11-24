package com.pxy.lambda.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author puxy
 * @version 1.0
 * @description 基于 函数式编程 实现一个 构造者
 * @date 2022/11/24 00:35
 */
public class Builder<T> {

    private final Supplier<T> constructor;// 构造方法

    private final List<Consumer<T>> injects = new ArrayList<>();// set方法

    private Builder(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    // 返回一个构造器
    public static <T> Builder<T> builder(Supplier<T> constructor) {
        return new Builder<>(constructor);
    }

    // 传入对应的 set 方法 和 属性值
    // todo 这个方法的 入参不太明白，InjectConsumer 明明是2个参数 无返回的函数，为什么传入 Cat::setName 等一个参数的方法不报错呢
    // 答：因为 Cat::setName  等价于  (cat,name)->cat.setName(name) 所以就 满足了 InjectConsumer.accept(T t, P p) 两参数无返回值
    public <P> Builder<T> with(Builder.InjectConsumer<T, P> consumer, P p) {
        // 将 两参数无返回  转换为  一参数无返回
        Consumer<T> c = inject -> consumer.accept(inject, p);
        injects.add(c);
        return this;
    }

    public T build() {
        // 执行构造方法 创建对象实例
        T t = constructor.get();
        // 遍历func集合  执行 一参数无返回func.accept(对象) 相当于 调用了 InjectConsumer.accept(T t, P p), 也就相当于调用了setter (cat,name)->cat.setXXX(xx)
        injects.forEach(inject -> inject.accept(t));

        return t;
    }


    @FunctionalInterface
    public interface InjectConsumer<T, P> {
        void accept(T t, P p);
    }

}



