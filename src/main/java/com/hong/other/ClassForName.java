package com.hong.other;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/22 15:02
 * @Version V1.0
 *
 **/
public class ClassForName {
    static {
        System.out.println("static code block");
    }

    private static String staticField = staticMethod();

    public static String staticMethod(){
        System.out.println("static method");
        return "给静态字段赋值";
    }
}
