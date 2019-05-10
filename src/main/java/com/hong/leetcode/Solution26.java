package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-10 14:08
 * Description:
 * 26. 删除排序数组中的重复项
 */
public class Solution26 {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int i = len - 1;
        while (i > 0) {
            int j = i - 1;
            if (nums[i] == nums[j]) {
                for (int k = i; k < len - 1; k++) {
                    nums[k] = nums[k + 1];
                }
                nums[len - 1] = 0;
                len--;
            }
            i = j;
        }

        return len;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

}
