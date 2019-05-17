package com.hong.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-05-17 09:12
 * Description:
 * 70. 爬楼梯
 */
public class Solution70 {

    /**
     * 1.直接递归，超出时间限制，重复计算太多
     * 时间复杂度：O(2 ^ N)
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 2.备忘录算法，缓存中间结果
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        return climbStairs(n, new HashMap<>());
    }


    public static int climbStairs(int n, Map<Integer, Integer> map) {
        if (n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int value = climbStairs(n - 1, map) + climbStairs(n - 2, map);
        map.put(n, value);
        return value;
    }

    /**
     * 动态规划，自底向上
     * @param n
     * @return
     */
    public static int climbStairs3(int n) {
        if (n < 1){
            return 0;
        }

        if (n == 1){
            return 1;
        }

        if (n == 2){
            return 2;
        }

        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i=3;i<=n;i++){
            temp = a + b;
            a = b;
            b = temp;
        }

        return temp;
    }

}
