package com.hong.leetcode;

/**
 * @author wanghong
 * @date 2019/05/03 16:54
 * 387. 字符串中的第一个唯一字符
 **/
public class Solution387 {

    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }

        return -1;
    }

}
