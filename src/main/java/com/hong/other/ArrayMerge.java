package com.hong.other;

import com.hong.sort.QuickSort;
import com.hong.sort.SortTestHelper;

/**
 * Created by John on 2019/1/24.
 */
public class ArrayMerge {
    public static void main(String[] args) {
        int[] arr1 = SortTestHelper.generateRandomArray(20, 1, 100);
        int[] arr2 = SortTestHelper.generateRandomArray(15, 1, 100);
        QuickSort.sort(arr1);
        QuickSort.sort(arr2);
        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] result = merge(arr1, arr2);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(SortTestHelper.isSorted(result));

        System.out.println("=======================");

        int[] arr3 = new int[10];
        for (int i = 10; i < 15; i++) {
            arr3[i - 10] = i + 1;
        }
        int[] arr4 = {5, 6, 7, 8, 9};
        merge(arr3, 5, arr4, 5);
        System.out.println(arr3.length);
        for (int i : arr3) {
            System.out.print(i + " ");
        }

        int m = 3, n = 3;
        System.out.println(m-- + n-- + 1);
        System.out.println(m + " " + n);
    }

    /**
     * 将两个有序数组合并为一个有序数组
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * 说明:
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        /**
         * 优化：如果其中一个数组的元素均大于另一个数组的元素，则可以直接组合，不用拆分。
         * 即：其中一个数组的第一个元素大于或者等于另一个数组的最后一个元素
         */
        if (arr1[0] >= arr2[arr2.length - 1]) {
            return connectArray(arr2, arr1);
        }

        if (arr2[0] >= arr1[arr1.length - 1]) {
            return connectArray(arr1, arr2);
        }

        int l = 0;
        int r = 0;
        int k = 0;
        int[] result = new int[arr1.length + arr2.length];
        /**
         * 按位循环比较两个数组，较小元素的放入新数组，
         * 下标加一（注意，较大元素对应的下标不加一），直到某一个下标等于数组长度时退出循环
         */
        while (l < arr1.length && r < arr2.length) {
            if (arr1[l] <= arr2[r]) {
                result[k++] = arr1[l++];
            } else {
                result[k++] = arr2[r++];
            }
        }

        /**
         *  后面两个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入
         *  此时较短数组已经全部放入新数组，较长数组还有部分剩余，最后将剩下的部分元素放入新数组，大功告成
         */
        while (l < arr1.length) {
            result[k++] = arr1[l++];
        }

        while (r < arr2.length) {
            result[k++] = arr2[r++];
        }

        return result;
    }

    /**
     * 88. 合并两个有序数组
     * https://leetcode-cn.com/problems/merge-sorted-array/
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 nums1 成为一个有序数组。
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

    /**
     * 合并数组，将arr2[]数组整个追加到arr1[]的后面，返会合并后的数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] connectArray(int[] arr1, int[] arr2) {
        int[] mergeArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, mergeArray, 0, arr1.length);
        System.arraycopy(arr2, 0, mergeArray, arr1.length, arr2.length);
        return mergeArray;
    }
}
