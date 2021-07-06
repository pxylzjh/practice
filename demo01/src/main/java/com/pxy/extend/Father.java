package com.pxy.extend;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2021/5/27 18:14
 */
public class Father {

    private String name;

    public Father(String name){

    }

    public void method (String name){

        this.name=name;

    }


    public void method_01(){
        String str = name.toUpperCase();
        System.out.println(name);
    }





}
