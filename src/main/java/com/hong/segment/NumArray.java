package com.hong.segment;

/**
 * @author wanghong
 * @date 2019/04/21 0:26
 * <p>
 * Leetcode 303. 区域和检索 - 数组不可变
 **/
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(2,5));
    }

}
