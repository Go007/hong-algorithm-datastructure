package com.hong.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wanghong
 * @date 2019/05/04 21:29
 * 219. 存在重复元素 II(滑动窗空)
 **/
public class Solution219 {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int l = 0, r = 0;
        while (l < nums.length) {

            while (++r < nums.length && r - l <= k) {
                if (nums[r] == nums[l]) {
                    return true;
                }
            }

            r = ++l;
        }

        return false;
    }

    /**
     * 使用滑动窗口与查找表来解决。
     * <p>
     * 设置查找表record，用来保存每次遍历时插入的元素，record的最大长度为k
     * 遍历数组nums，每次遍历的时候在record查找是否存在相同的元素，如果存在则返回true，遍历结束
     * 如果此次遍历在record未查找到，则将该元素插入到record中，而后查看record的长度是否为k + 1
     * 如果此时record的长度是否为k + 1，则删减record的元素，该元素的值为nums[i - k]
     * 如果遍历完整个数组nums未查找到则返回false
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 2;
        System.out.println(containsNearbyDuplicate2(nums, k));
    }
}
