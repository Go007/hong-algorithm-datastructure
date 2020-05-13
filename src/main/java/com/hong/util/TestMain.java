package com.hong.util;

/**
 * @author wanghong
 * @date 2020/04/25 14:45
 **/
public class TestMain {
    public static void main(String[] args) {
        String str = "*c*m*b*n*t*";
        System.out.println(move(str));
    }

    public static String move(String str) {
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('*' == c) {
                sb0.append(c);
            } else {
                sb1.append(c);
            }
        }

        return sb0.append(sb1.toString()).toString();
    }
}
