package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-23 17:06
 * Description:
 * 541. 反转字符串 II
 */
public class Solution541 {

    public static String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] chr = s.toCharArray();
        int size = s.length();
        int begin = 0;
        int end = Math.min(size, 2 * k) - 1;

        while (end - begin > 0) {
            int diff = end - begin + 1;
            if (diff >= k) {
                reverse(chr, begin, begin + k - 1);
                begin = end + 1;
                end += Math.min(size - end - 1, 2 * k);
            } else {
                reverse(chr, begin, end);
                break;
            }
        }

        return new String(chr);
    }

    /**
     * @param s
     * @param l
     * @param r
     */
    public static void reverse(char[] s, int l, int r) {
        while (l <= r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 8;
        String str = reverseStr(s, k);
        System.out.println(str);
    }

}
