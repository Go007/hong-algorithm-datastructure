package com.hong.leetcode;

/**
 * @author ：wanghong
 * @date ：2020-11-10 09:17
 * @description： 80. 删除排序数组中的重复项 II
 */
public class Solution80 {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            // 如果重复数字个数不大于2，那么这个判断肯定是成立的
            // 如果重复个数大于2，那么nums[i-2] < n 就是不成立的
            // 即多余重复项都被忽视了，直到遇到新的数字或者数字结束
            if (i < 2 || n > nums[i - 2]) nums[i++] = n;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Solution80 s = new Solution80();
        int len = s.removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

}
