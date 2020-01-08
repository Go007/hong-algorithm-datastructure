package com.hong.other;

/**
 * @author wanghong
 * @date 2020/01/08 23:08
 * 构造代码块：直接在类中定义且没有static修饰没有名字的{}代码块称为构造代码块
 * 在每次创建对象时都会被调用，并且构造代码块的执行次序优先于类构造函数
 **/
public class CodeBlock2 {

    {
        System.out.println("构造块333");
    }

    public CodeBlock2() {
        System.out.println("构造方法222");
    }

    {
        System.out.println("构造快111");
    }

    public static void main(String[] args) {
        new CodeBlock2();
        System.out.println("==================");
        new CodeBlock2();
    }
}
