package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-09 09:50
 * Description:
 * 344. 反转字符串
 */
public class Solution344 {

    public static void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l <= r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
        for (char c:s){
            System.out.print(c + "->");
        }
    }

}
