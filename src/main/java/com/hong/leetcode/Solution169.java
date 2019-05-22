package com.hong.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-05-22 17:28
 * Description:
 * 169. 求众数
 */
public class Solution169 {

    /**
     * 位运算 分治算法
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int count = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > count) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 在数组排序后，众数一定会出现在数组的中间位置
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法  不使用Map的线性算法
     *
     * @param nums
     * @return
     */
    public static int majorityElement3(int[] nums) {
        int ret = nums[0];
        int count = 1;
        for (int num : nums) {
            if (num != ret) {
                count--;
                if (count == 0) {
                    count = 1;
                    ret = num;
                }
            } else
                count++;
        }
        return ret;
    }

}

