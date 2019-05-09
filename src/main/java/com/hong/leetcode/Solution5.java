package com.hong.leetcode;

/**
 * @author wanghong
 * @date 2019/05/09 18:43
 * 5. 最长回文子串
 **/
public class Solution5 {

    /**
     * 暴力法
     * 时间复杂度:O(n^3)
     * 空间复杂度：O(1)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        for (int i = n; i > 0; i--) {
            for (int j = 0; j <= n - i; j++) {
                String sub = s.substring(j, i + j);
                if (isPalindrome(sub)) {
                    return sub;
                }
            }
        }

        return "";
    }

    /**
     * 判断给定的字符串是否为回文字符串
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "ddbacabhh";
        System.out.println(longestPalindrome(s));
    }
}
