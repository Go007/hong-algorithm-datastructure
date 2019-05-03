package com.hong.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wanghong
 * @date 2019/05/03 14:36
 * 239. 滑动窗口最大值
 **/
public class Solution239 {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int size = nums.length;
        int[] res = new int[size - k + 1];

        for (int l = 0; l <= size - k; l++) {
            int max = nums[l];
            for (int r = l + 1; r <= l + k - 1; r++) {
                if (nums[r] > max) {
                    max = nums[r];
                }
            }
            res[l] = max;
        }

        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int size = nums.length;
        int[] res = new int[size - k + 1];

        k = Math.min(k, size);
        // 使用LinkedList作为双端队列,存放数组元素的索引
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            // 在双端队列的尾部添加元素，并保证左边元素都比尾部大
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            /**
             *   如果队首索引与当前遍历的索引相差k个位置，
             *   说明队首索引已经不在当前滑动窗空内，直接移除
             */
            if (deque.getFirst() == i - k) {
                deque.removeFirst();
            }

            // 记录当前滑动窗口的最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        for (int i : res) {
            System.out.print(i + ",");
        }

        System.out.println();
    }
}
