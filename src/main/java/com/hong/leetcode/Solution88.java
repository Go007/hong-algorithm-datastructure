package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-23 16:36
 * Description:
 * 88. 合并两个有序数组
 */
public class Solution88 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 5;
        int res = m-- + n-- - 1;
        System.out.println(res + " " + m + " " + n);
    }
}
