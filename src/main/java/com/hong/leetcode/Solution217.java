package com.hong.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wanghong
 * Date 2019-05-10 19:13
 * Description:217. 存在重复元素
 */
public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int i:nums){
            if (set.contains(i)){
                return true;
            }else {
                set.add(i);
            }
        }

        return false;
    }

}
