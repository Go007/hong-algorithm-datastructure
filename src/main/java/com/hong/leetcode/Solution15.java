package com.hong.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wanghong
 * @date 2019/11/04 22:28
 * 15. 三数之和
 **/
public class Solution15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = null;
        Set<String> set = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0){
                       // if (!contains(set,nums[i], nums[j], nums[k])){
                            list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            result.add(list);
                      //  }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        result.forEach(l -> System.out.println(l));
    }

    private static boolean contains( Set<String> set,int i,int j,int k){
        String s0 = i + j + k + "";
        String s1 = i + k + j + "";
        String s2 = j + i + k + "";
        String s3 = j + k + i + "";
        String s4 = k + i + j + "";
        String s5 = k + j + i + "";
        if (set.contains(s0) || set.contains(s1) || set.contains(s2)
            || set.contains(s3) || set.contains(s4) || set.contains(s5)){
            return true;
        }

        set.add(s0);
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        set.add(s5);
        return false;
    }
}
