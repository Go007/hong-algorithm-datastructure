package com.hong.leetcode;


import java.util.PriorityQueue;

/**
 * Created by wanghong
 * Date 2019-05-06 10:04
 * Description:
 * 215. 数组中的第K个最大元素
 */
public class Solution215 {

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

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(findKthLargest(nums,k));
    }
}
