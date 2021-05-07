package com.pxy.bcrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Slf4j
public class TestBcrypt {

    public static void main(String[] args) {
        String pwd = "123456";
        String pxy = BCrypt.gensalt("$2y");
        System.out.println(pxy);
        String hashpw = BCrypt.hashpw(pwd, pxy);
        System.out.println(hashpw);
        //获取盐
        String gensalt = BCrypt.gensalt();
        System.out.println(gensalt);
        //加密
        String hashpw01 = BCrypt.hashpw(pwd, gensalt);
        System.out.println(hashpw01);
        String gensalt_12 = BCrypt.gensalt(12);
        System.out.println(gensalt_12);
        String hashpw_12 = BCrypt.hashpw(pwd, gensalt_12);
        System.out.println(hashpw_12);
        //校验
        boolean checkpw = BCrypt.checkpw(pwd, hashpw01);
        System.out.println(checkpw+"");
    }
}
