package com.pxy.other;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2022/4/12 14:46
 */
public class Other {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.forEach(e->{});
        list.stream().map(e->e.length()).collect(Collectors.toList());
    }

}
