package com.pxy.thread.thread_local;


import java.util.HashMap;
import java.util.Map;

/**
 * @author puxy
 * @version 1.0
 * @description threadLocal 学习
 * @date 2022/11/20 16:59
 */
public class TestThreadLocal {
   public static final ThreadLocal<Map<String, String>> local = new ThreadLocal<>();
    public static final ThreadLocal<Map<String, String>> local1 = new ThreadLocal<>();
    /**
     *
     * ThreadLocal:线程变量
     *
     *
     *
     */
    private static final ThreadLocal T = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            System.out.println(super.initialValue());
            return super.initialValue();
        }
    };

    public static void main(String[] args) {
        local.set(new HashMap<>());
        local1.set(new HashMap<>());
        method();

        Map<String, String> map = local.get();
        Map<String, String> map1 = local1.get();
        String s = map.get("s");
        String s1 = map1.get("sx");
        System.out.println(s);
        System.out.println(s1);

    }

    private static void method() {
        String s = local.get().put("s", "123");
        String s1 = local1.get().put("sx", "45123");
        System.out.println(s);
    }


}
