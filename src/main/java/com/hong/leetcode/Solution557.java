package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-09 10:10
 * Description:
 * 557. 反转字符串中的单词 III
 */
public class Solution557 {

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for (String ss : strs) {
            char[] c = ss.toCharArray();
            int l = 0, r = c.length - 1;
            while (l <= r) {
                char temp = c[l];
                c[l] = c[r];
                c[r] = temp;
                l++;
                r--;
            }
            sb.append(new String(c))
              .append(" ");
        }

        sb.deleteCharAt(sb.lastIndexOf(" "));
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }

}
