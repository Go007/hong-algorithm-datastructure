package com.hong.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-05-22 16:54
 * Description:
 * 136. 只出现一次的数字
 */
public class Solution136 {

    /**
     * 利用Hash表，Time: O(n) Space: O(n)
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1; // can't find it.
    }

    /**
     * 位运算 Time: O(n) Space: O(1)
     *
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * 任何数于0异或为任何数 0 ^ n => n
     * 相同的数异或为0: n ^ n => 0
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
