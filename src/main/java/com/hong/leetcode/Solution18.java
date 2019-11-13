package com.hong.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wanghong
 * @date 2019/11/13 21:18
 * 18. 四数之和
 **/
public class Solution18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return result;
        }

        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0;i < len-3;i++){
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j = i + 1;j < len - 2;j++){
                if (j > i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                int l = j + 1;
                int r = len - 1;
                while (l < r){
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while (l < r && nums[l] == nums[l+1]){
                            l++;
                        }
                        while (r > l && nums[r] == nums[r-1]){
                            r--;
                        }
                        l++;
                        r--;
                    }else if (sum < target){
                        l++;
                    }else {
                        r--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        List<List<Integer>> result = fourSum(nums,-11);
        System.out.println(result);
    }
}
