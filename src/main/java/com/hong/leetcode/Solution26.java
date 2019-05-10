package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-10 14:08
 * Description:
 * 26. 删除排序数组中的重复项
 */
public class Solution26 {

    /**
     * 双指针法
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
            }
            nums[i] = nums[j];
        }
        return i + 1;
    }

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

    public static int removeDuplicates3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int i = len - 1;

        while (i > 0) {
            if (nums[i] != nums[i - 1]) {
                i--;
                continue;
            }

            int r = i;
            while (i > 0 && nums[i] == nums[i - 1]) {
                i--;
            }

            int j = i;
            for (int k = r + 1; k < len; k++) {
                nums[++j] = nums[k];
            }

            len = len - (r - i);
            i--;
        }

        return len;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates2(nums);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
