package com.pxy.class_loader;

import sun.misc.Launcher;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/7/7 10:32
 */
public class Test {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        /**
         * 自定义类加载器
         * 下级类加载器无法加载
         */
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                //A.class
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream in = getClass().getResourceAsStream(fileName);
                try {
                    if (in == null) {
                        //如果自定义类加载器加载不到了,就委托它的父级加载器AppClassLoader加载
                        Object o = this.getParent().loadClass(name).newInstance();
                        System.out.println(this.getParent().getClass());
                        System.out.println(o.getClass());
                        return this.getParent().loadClass(name);
                    }
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    // 创建对象 并 校验
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object o = myClassLoader.loadClass("com.pxy.class_loader.A").newInstance();
        System.out.println(o.getClass());
        /**
         * 输出false说明o和com.pxy.class_loader.A不是同一个类,这显然是不正常的
         * 其实是因为不同的类加载器都有自己的命名空间,所以不同的类加载器加载了同一个类时,对于JVM来说时两个不同的类
         */
        System.out.println(o instanceof com.pxy.class_loader.A);

        /**
         * 使用自定义类加载器加载Object类时,会报空指针,说明 下级加载器无法加载上级加载器路径下的类
         */
        o = myClassLoader.loadClass("java.lang.Object").newInstance();
        /**
         * 尝试加载自定义的java.lang.String1,会报错 禁止使用 java.lang 作为包名
         */
        Class<?> aClass = myClassLoader.loadClass("java.lang.String1");
        System.out.println(aClass);
    }


}
