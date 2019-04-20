package com.hong.segment;

/**
 * @author wanghong
 * @date 2019/04/21 0:43
 * <p>
 * 307. 区域和检索 - 数组可修改
 * 使用sum数组的思路：TLE
 **/
public class NumArray3 {
    private int[] data;
    private int[] sum;

    public NumArray3(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public void update(int index, int val) {
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + data[i - 1];
        }
    }

    public void update2(int index, int val) {
        int oldVal = data[index];
        data[index] = val;
        int diff = val - oldVal;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] += diff;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray3 numArray3 = new NumArray3(nums);
        System.out.println(numArray3.sumRange(0, 2));
        numArray3.update2(1, 2);
        System.out.println(numArray3.sumRange(0, 2));
    }
}
