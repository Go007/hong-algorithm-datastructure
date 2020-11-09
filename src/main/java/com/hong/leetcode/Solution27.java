package com.hong.leetcode;

/**
 * @author ：wanghong
 * @date ：2020-11-09 18:16
 * @description：27. 移除元素
 */
public class Solution27 {

    public int removeElement(int[] nums, int val) {
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

    /**
     * 双指针法
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

    public int removeElement3(int[] nums, int val) {
        int i = 0;
        int k = nums.length;
        while (i < k) {
            if (nums[i] != val) {
                i++;
            } else {
               // nums[i] = nums[k - 1];
               // k--;
                nums[i] = nums[--k];
            }
        }

        return k;
    }

    public static void main(String[] args) {
        Solution27 s = new Solution27();
        int[] nums = {3, 3, 2, 7, 2, 3, 5};
        int len = s.removeElement3(nums, 3);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

}
