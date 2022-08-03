package com.pxy.regex;

import java.util.Objects;

/**
 * @author puxy
 * @version 1.0
 * @description 正则匹配 - 脱敏
 * @date 2022/6/15 20:21
 */
public class DesensitizeUtil {

    /**
     * 手机号
     */
    private static final String MOBILE_REGEX = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 身份证
     */
    private static final String ID_CARD_REGEX = "(\\d{3})\\d{11}(\\d{4})";

    /**
     * 邮箱
     */
    private static final String EMAIL_REGEX = "(^\\w{2})[^@]*(@.*$)";

    /**
     * 手机号
     *
     * @param str
     * @return
     */
    public static String mobileDesensitization(String str) {
        return Objects.isNull(str) ? "" : str.replaceAll(MOBILE_REGEX, "$1****$2");
    }

    /**
     * 身份证
     *
     * @param str
     * @return
     */
    public static String idCardDesensitization(String str) {
        return Objects.isNull(str) ? "" : str.replaceAll(ID_CARD_REGEX, "$1***********$2");
    }

    /**
     * 名字
     *
     * @param str
     * @return
     */
    public static String nameDesensitization(String str) {
        return Objects.isNull(str) ? "" : "*" + str.substring(1);
    }

    /**
     * 邮箱
     *
     * @param str
     * @return
     */
    public static String emailDesensitization(String str) {
        return Objects.isNull(str) ? "" : str.replaceAll(EMAIL_REGEX, "$1****$2");
    }
}
