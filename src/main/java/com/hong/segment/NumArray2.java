package com.hong.segment;

/**
 * @author wanghong
 * @date 2019/04/21 0:37
 * <p>
 * 303. 区域和检索 - 数组不可变
 **/
public class NumArray2 {
    private int[] sum; // sum[i]存储前i个元素和, sum[0] = 0

    // 即sum[i]存储nums[0...i-1]的和
    // sum(i, j) = sum[j + 1] - sum[i]
    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 numArray2 = new NumArray2(nums);
        System.out.println(numArray2.sumRange(2,5));
    }
}
