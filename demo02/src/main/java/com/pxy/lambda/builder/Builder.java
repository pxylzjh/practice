package com.pxy.lambda.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author puxy
 * @version 1.0
 * @description 基于lambda实现一个 构造者
 * @date 2022/11/24 00:35
 */
public class Builder<T> {

    private final Supplier<T> constructor;

    private final List<Consumer<T>> injects = new ArrayList<>();

    private Builder(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    public static <T> Builder<T> builder(Supplier<T> constructor) {
        return new Builder<>(constructor);
    }

    public <P> Builder<T> with(Builder.InjectConsumer<T, P> consumer, P p) {

        Consumer<T> c = inject -> consumer.accept(inject, p);
        injects.add(c);
        return this;
    }

    public T build() {

        T t = constructor.get();

        injects.forEach(inject -> inject.accept(t));

        return t;
    }


    @FunctionalInterface
    public interface InjectConsumer<T, P> {
        void accept(T t, P p);
    }

}



