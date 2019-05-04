package com.hong.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-04-30 15:21
 * Description:
 * 3. 无重复字符的最长子串
 * 滑动窗口
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

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        /**
         * 使用Map维护字符到索引的映射
         * 假设s[j]=c,Map当前Key的索引范围[i,j)，
         * 此时Map的key包含c，且map.get(c) = k,
         * 将原来的滑动窗口[i,j)切分为两个窗口 [i,k] [k+1,j]，
         * 取较大窗口的值即为当前不重复字符串的最大值，
         * 将i滑动到k+1位置
         * 双指针
         */

        int maxLength = 0;
        // current index of Character
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        // try to extend the range [i, j]
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            maxLength = Math.max(maxLength, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }

        return maxLength;
    }

    /**
     * 建立一个256位大小的整型数组 freg ，用来建立字符和其出现次数之间的映射。
     * <p>
     * 维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
     * <p>
     * 如果当前遍历到的字符从未出现过，那么直接扩大右边界；
     * 如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
     * 重复（1）（2），直到左边索引无法再移动；
     * 维护一个结果res，每次用出现过的窗口大小来更新结果 res，最后返回 res 获取结果。
     * <p>
     * 时间复杂度: O(len(s))
     * 空间复杂度: O(len(charset))
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int[] freq = new int[256];
        int l = 0, r = -1;
        int res = 0;
        char[] chars = s.toCharArray();
        int size = chars.length;
        while (l < size) {
            if (r + 1 < size && freq[chars[r + 1]] == 0) {
                r++;
                freq[chars[r]]++;
            } else {
                freq[chars[l]]--;
                l++;
            }
            res = Math.max(res, r-l+1);
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "bdvdf";
        System.out.println(lengthOfLongestSubstring3(s));
    }

}
