package com.hong.leetcode;


import java.util.PriorityQueue;

/**
 * Created by wanghong
 * Date 2019-05-06 10:04
 * Description:
 * 215. 数组中的第K个最大元素
 */
public class Solution215 {

    /**
     * 堆
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return -1;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            if (queue.size() < k) {
                queue.add(n);
            } else if (n > queue.peek()) {
                queue.remove();
                queue.add(n);
            }
        }

        return queue.peek();
    }

    /**
     * 分治算法,快速排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return -1;
        }

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = nums[l];
            int j = l;
            for (int i = l + 1; i <= r; i++) {
                if (nums[i] > mid) {
                    j++;
                    swap(nums, i, j);
                }
            }
            swap(nums, l, j);

            /**
             * 经过上面一轮比较和交换后，下面的关系式成立：
             * nums[l...j-1] > nums[j] >= nums[j+1...r]
             * 此时nums[j]即为第(j+1)大的元素
             */
            if (j == k - 1) {
                return nums[j];
            } else if (j < k - 1) {
                l = j + 1;
            } else {
                r = j - 1;
            }
        }

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(findKthLargest2(nums, k));
    }
}
