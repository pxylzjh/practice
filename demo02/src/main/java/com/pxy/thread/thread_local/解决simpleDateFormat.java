package com.pxy.thread.thread_local;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author puxy
 * @version 1.0
 * @description ThreadLocal解决SimpleDateFormat线程安全问题
 * @date 2023/2/26 00:17
 */
public class 解决simpleDateFormat {
    // 当采用全局的 SimpleDateFormat 对象时 还是会有线程安全问题
//    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    // 感觉其实本质还是 给每个线程都new了一个 SimpleDateFormat对象
//    static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(()-> {
//        return sdf;
//    });

    // 感觉其实本质还是 给每个线程都new了一个 SimpleDateFormat对象，但是，我之前以为和使用的时候每次都new一个sdf的效果是一样的，其实不一样，采用threadLocal
    // 每个线程只会创建一个sdf，而每次手动new的话，每个线程都会创建一个sdf对象
    static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {

        testParse();

    }

    /**
     */
    private static void testParse() {

        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                try {
                    Date parse = threadLocal.get().parse("2011-01-01");
                    System.out.println(parse);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

}
