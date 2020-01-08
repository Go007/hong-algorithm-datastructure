package com.hong.other;

/**
 * @author wanghong
 * @date 2020/01/08 22:44
 **/
public class TestEquals2 {
    public static void main(String[] args) {
        /**
         * 1. String s1 = "abc";
         *    String s2 = new String("abc"); // 这一行代码只生成一个String对象，因为 "abc" 在常量池中已经存在，但new 操作会在堆中生成一个新的
         *
         * 2. String s2 = new String("abc"); // 这一行代码会生成两个String对象 ，"abc" 和 new
         *    String s1 = "abc";
         */
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = "abc";
        String s4 = "xxx";
        String s5 = "abc" + "xxx";
        String s6 = s3 + s4;

        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s5 == s6);

        /**
         * 返回字符串对象的规范表示形式
         * 字符串池最初为空，由类字符串私下维护
         * 调用intern()，如果池中已包含由equals(Object)方法确定的与此String对象
         * 相等的字符串，则返回池中的字符串，否则，将此字符串对象添加到池中，
         * 并返回对此字符串对象的引用。
         * 因此，对于任意两个字符串，s和t，s.intern() == t.intern()在且仅当
         * se.equals(t)为true时为true。
         * 所有文字字符串和字符串值常量表达式都会被插入。
         * String文字在Java语言规范3.3.5节中定义。
         */
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
    }
}
