package com.hong.leetcode;

/**
 * @author ：wanghong
 * @date ：2020-11-09 18:16
 * @description：27. 移除元素
 */
public class Solution27 {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int k = nums.length - 1;
        int i = 0;
        for (; i <= k; i++) {
            if (nums[i] != val) {
                continue;
            }

            while (k > i && nums[k] == val) {
                k--;
            }

            if (k <= i) {
                break;
            }

            nums[i] = nums[k];
            k--;
        }

        return i;
    }

    public static void main(String[] args) {
        Solution27 s = new Solution27();
        int[] nums = {3,3,2,7,2,3,5};
        int len = s.removeElement(nums, 3);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

}
