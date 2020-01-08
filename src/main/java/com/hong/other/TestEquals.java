package com.hong.other;

import com.hong.cas.User;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wanghong
 * @date 2020/01/08 22:13
 *  equals  ==
 *   == 引用类型：比较地址  基本类型：比较值
 *   只要是new 出来的 == 肯定是false
 *   equals 比较要看方法是否被覆写
 **/
public class TestEquals {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        Set<String> set01 = new HashSet<>();
        set01.add(s1);
        set01.add(s2);
        System.out.println(set01.size());

        Integer n1 = new Integer(100);
        Integer n2 = new Integer(100);
        System.out.println(n1 == n2);
        System.out.println(n1.equals(n2));

        Integer n3 = 100;
        Integer n4 = 100;
        System.out.println(n3 == n4);
        System.out.println(n3.equals(n4));

        // 常量池 -128 - +129,超出会在堆上分配
        Integer n5 = 129;
        Integer n6 = 129;
        System.out.println(n5 == n6);
        System.out.println(n5.equals(n6));
        System.out.println("===========================");

        User u1 = new User("abc");
        User u2 = new User("abc");
        System.out.println(u1 == u2);
        System.out.println(u1.equals(u2));
        Set<User> set02 = new HashSet<>();
        set02.add(u1);
        set02.add(u2);
        System.out.println(set02.size());
    }
}
