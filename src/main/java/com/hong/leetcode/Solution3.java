package com.hong.leetcode;

import java.util.LinkedList;

/**
 * Created by wanghong
 * Date 2019-04-30 15:21
 * Description:
 * 3. 无重复字符的最长子串
 */
public class Solution3 {


    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        LinkedList<Character> list = new LinkedList<>();
        int maxLength = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (list.contains(c)) {
                int size = list.size();
                maxLength = Math.max(size, maxLength);

                int index = list.indexOf(c);
                for (int i = size - 1; i >= index; i--) {
                    list.remove(i);
                }
            }
            list.addFirst(c);
        }

        return Math.max(list.size(), maxLength);
    }

    public static void main(String[] args) {
        String s = "bdvdf";
        System.out.println(lengthOfLongestSubstring(s));
    }

}
