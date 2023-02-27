package com.pxy.thread.thread_local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author puxy
 * @version 1.0
 * @description 复现simpleDateFormat线程安全问题
 * @date 2023/2/25 18:53
 */
public class 复现simpleDateFormat {

   static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
//        testFormat();

         testParse();

    }
    // SimpleDateFormat.parse()方法线程安全问题复现
    private static void testFormat() {
        // 创建 10 个 不同的日期，每个线程处理一个
        ArrayList<Date> dates = new ArrayList(){{
            add(new Date(2011-1900, Calendar.JANUARY,1));
            add(new Date(2012-1900, Calendar.JANUARY,1));
            add(new Date(2013-1900, Calendar.JANUARY,1));
            add(new Date(2014-1900, Calendar.JANUARY,1));
            add(new Date(2015-1900, Calendar.JANUARY,1));
            add(new Date(2016-1900, Calendar.JANUARY,1));
            add(new Date(2017-1900, Calendar.JANUARY,1));
            add(new Date(2018-1900, Calendar.JANUARY,1));
            add(new Date(2019-1900, Calendar.JANUARY,1));
            add(new Date(2020-1900, Calendar.JANUARY,1));
        }};

        for (int i = 0; i < 10; i++) {

            int finalI = i;
            new Thread(()->{

                String format = sdf.format(dates.get(finalI));
                System.out.println(Thread.currentThread().getName()+"--格式化前"+dates.get(finalI));
                System.out.println(Thread.currentThread().getName()+"--格式化后"+format);

            },i+"").start();

        }
    }

    /**
     * SimpleDateFormat.parse()方法线程安全问题复现
     */
    private static void testParse() {

        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                try {
                    Date parse = sdf.parse("2011-01-01");
                    System.out.println(parse);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
