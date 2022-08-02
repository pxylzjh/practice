package com.pxy.string;

import java.text.MessageFormat;

/**
 * @author puxy
 * @version 1.0
 * @description 对string进行操作
 * @date 2022/3/30 11:21
 */
public class Format {


    public static void main(String[] args) {

        // 1.
        messageFormat();


    }

    /**
     * 使用 MessageFormat 对String操作
     */
    private static void messageFormat() {
        MessageFormat GLOBAL_FORMAT = new MessageFormat("com.taobao.tddl.atom.global.{0}.{1}");
        String format = GLOBAL_FORMAT.format(new Object[]{"omsdbnew", "pxy"});
        System.out.println(format);
    }

}
