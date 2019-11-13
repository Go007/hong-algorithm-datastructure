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
            // 跳过起点值相同元素，避免重复解
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // 排序后最小sum值
            if (nums[i] << 2 > target){
                break;
            }
            for (int j = i + 1;j < len - 2;j++){
                if (j > i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                // l,r构成滑动窗口
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
        System.out.println("===================");
        List<List<Integer>> result2 = fourSum2(nums,-11);
        System.out.println(result2);
    }

    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return ans;
        }

        Arrays.sort(nums);
        int len = nums.length;

        // i为起点，j为终点，l，r为滑动窗口的两端
        for (int i = 0;i < len - 3;i++){
            // 最小sum > target，直接终止循环
            if (nums[i] << 2 > target){
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j = len - 1;j > i + 2;j--){
                // 最大sum < target,跳出本层循环
                if (nums[j] << 2 < target){
                    break;
                }
                if (j < len - 1 && nums[j] == nums[j+1]){
                    continue;
                }
                int l = i + 1;
                int r = j - 1;
                while (l < r){
                    int sum = nums[i] + nums[l] + nums[r] + nums[j];
                    if (sum == target){
                        ans.add(Arrays.asList(nums[i],nums[l],nums[r],nums[j]));
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
        return ans;
    }
}
