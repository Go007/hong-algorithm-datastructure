package com.hong.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghong
 * @date 2019/05/23 21:19
 * 1. 两数之和
 **/
public class Solution1 {

    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        /**
         * 不止是考虑了时间复杂度，因为是先查找再放入哈希表，
         * 如果两个一样的num加起来不等于target那么覆盖是没问题的，,
         * 也考虑了空间复杂度最小的问题
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)){
                return new int[]{map.get(diff),i};
            }
            map.put(nums[i],i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums= {2,11,7,15};
        int[] index = twoSum(nums, 9);
        for (int i:index){
            System.out.println(i);
        }
    }
}
