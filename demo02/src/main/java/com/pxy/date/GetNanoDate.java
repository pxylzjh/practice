package com.pxy.date;

/**
 * @author puxy
 * @version 1.0
 * @description 获取纳秒日期
 * @date 2022/4/28 13:14
 */
public class GetNanoDate {

    // TODO: 2022/4/28 还不知道如何在java中获取纳秒级别的日期,只能获取到当前的纳秒时间戳
    public static void main(String[] args) {

        long nanoTime = System.nanoTime();
        System.out.println("获取当前纳秒时间戳："+ nanoTime);

    }

}
