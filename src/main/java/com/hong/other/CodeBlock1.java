package com.hong.other;

/**
 * @author wanghong
 * @date 2020/01/08 23:04
 * 普通代码块：在方法或语句中出现的{ }
 * 普通代码块和一般语句的执行顺序由他们在代码块中的出现的次序决定，"先出现先执行"
 **/
public class CodeBlock1 {
    public static void main(String[] args) {
        {
            int x = 1;
            System.out.println("普通代码块内的变量：" + x);
        }

        int x = 2;
        System.out.println("main方法内变量：" + x);
    }
}
