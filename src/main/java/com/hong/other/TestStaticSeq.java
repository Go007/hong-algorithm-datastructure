package com.hong.other;

/**
 * @author wanghong
 * @date 2020/01/09 22:13
 **/
public class TestStaticSeq { // 主类

    static {
        System.out.println("888");
    }

    public static void main(String[] args) {
        System.out.println("777");
        new Son();
        System.out.println("===============");
        new Son();
        System.out.println("+++++++++++++++");
        new Father();
    }
}

class Father{
    public Father(){
        System.out.println("111");
    }

    {
        System.out.println("222");
    }

    static {
        System.out.println("333");
    }
}

class Son extends Father{
    public Son(){
        System.out.println("444");
    }

    {
        System.out.println("555");
    }

    static {
        System.out.println("666");
    }
}