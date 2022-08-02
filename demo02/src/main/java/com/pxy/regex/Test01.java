package com.pxy.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author puxy
 * @version 1.0
 * @description 正则
 * @date 2020/12/29 12:41
 */
public class Test01 {

    public static void main(String[] args) {
//        test01();

        String regex1 = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&.,*])(?![a-z0-9\\W]+$)(?![A-Z0-9\\W]+$)[0-9a-zA-Z!@#$%^&.,*0-9]{8,}$";
        String password = "*";

        boolean matches = password.matches(regex);
        System.out.println(matches);

        String pas = "(?=.*[!@#$%^&*.,])";

        boolean matches1 = password.matches(pas);
        System.out.println(matches1);

        String regex2 = "^(?=.*[!@#$%^&.,*])$";
        boolean matches2 = password.matches(regex2);
        System.out.println(matches2);

        String regex3 = "^(?![A-Za-z0-9]+$)";
        boolean matches3 = password.matches(regex3);
        System.out.println(matches3);

    }

    private static void test01() {
        //        String regex = "(?<=\\{).*?(?=\\})";
        String regex = "\\{.*?\\}";
        String str = "http://10.19.83.176:3100/locationAPI/special-subject-service/liveMonitoring/{visible}/{service}";
        Matcher compile = Pattern.compile(regex).matcher(str);
        //找出符合正则的所有字符
        while (compile.find()) {
            System.out.println(compile.group());
        }
    }
}
