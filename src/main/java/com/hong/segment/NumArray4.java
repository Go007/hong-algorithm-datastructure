package com.hong.segment;

/**
 * @author wanghong
 * @date 2019/04/21 1:45
 * 307. 区域和检索 - 数组可修改
 * 使用线段树实现
 **/
public class NumArray4 {
    private SegmentTree<Integer> segTree;

    public NumArray4(int[] nums) {
        if (nums.length != 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segTree == null)
            throw new IllegalArgumentException("Error");
        segTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segTree == null)
            throw new IllegalArgumentException("Error");
        return segTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray4 numArray4 = new NumArray4(nums);
        System.out.println(numArray4.sumRange(0, 2));
        numArray4.update(1, 2);
        System.out.println(numArray4.sumRange(0, 2));
    }
}
