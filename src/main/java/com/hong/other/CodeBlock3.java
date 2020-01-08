package com.hong.other;

/**
 * @author wanghong
 * @date 2020/01/08 23:15
 **/
public class CodeBlock3 { //主类
    {
        System.out.println("CodeBlock3的构造块444");
    }

    static {
        System.out.println("CodeBlock3的静态代码块555");
    }

    public CodeBlock3() {
        System.out.println("CodeBlock3的构造方法666");
    }

    public static void main(String[] args) {
        System.out.println("=========CodeBlock3.main()=========");
        new Code();
        System.out.println("---------------");
        new CodeBlock3();
    }
}

class Code{ //随从类

    public Code() {
        System.out.println("Code的构造方法1111");
    }

    {
        System.out.println("Code的构造块2222");
    }

    static {
        System.out.println("Code的静态代码块3333");
    }
}
