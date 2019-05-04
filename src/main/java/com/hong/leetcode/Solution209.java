package com.hong.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wanghong
 * @date 2019/05/04 18:31
 * 209. 长度最小的子数组(滑动窗口)
 **/
public class Solution209 {

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = nums.length;
        int sum = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {

            deque.addLast(nums[i]);
            sum += nums[i];

            if (deque.size() == nums.length && sum < s) {
                return 0;
            }

            while (sum >= s) {
                res = Math.min(res, deque.size());
                Integer first = deque.removeFirst();
                sum -= first;
            }
        }

        return res;
    }

    /**
     * 滑动窗口的思路
     * 定义两个指针 left 和 right ，分别记录子数组的左右的边界位置。
     * <p>
     * 让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾；
     * 更新最短距离，将 left 像右移一位, sum 减去移去的值；
     * 重复（1）（2）步骤，直到 right 到达末尾，且 left 到达临界位置
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int s, int[] nums) {
        int l = 0, r = -1;    // nums[l...r]为我们的滑动窗口
        int sum = 0;
        int result = nums.length + 1;
        while (l < nums.length) { // 窗口的左边界在数组范围内,则循环继续

            if (r + 1 < nums.length && sum < s) {
                r++;
                sum += nums[r];
            } else { // r已经到头 或者 sum >= s
                sum -= nums[l];
                l++;
            }

            if (sum >= s) {
                result = (r - l + 1) < result ? (r - l + 1) : result;
            }
        }
        if (result == nums.length + 1) {
            return 0;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));
    }
}
