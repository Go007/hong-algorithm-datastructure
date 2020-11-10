package com.hong.leetcode;

/**
 * @author ：wanghong
 * @date ：2020-11-10 17:20
 * @description：283. 移动零
 */
public class Solution283 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                if (i != j) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 0, 0, 1, 1, 0, 0, 3, 0, 12, 0};
        Solution283 s = new Solution283();
        s.moveZeroes(nums);
        for (int n : nums) {
            System.out.print(n + ",");
        }
    }
}
