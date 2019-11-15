package com.hong.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-11-15 14:43
 * Description:120. 三角形最小路径和
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * <p>
 * [i,j]表示第i(i从0开始)行第j(j从0开始)的下一行相邻节点 [i+1,j] [i+1,j+1]
 */
public class Solution120 {

    /**
     * 自底向上, DP 【AC】用迭代
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] minLen = new int[row + 1];
        for (int level = row - 1; level >= 0; level--) {
            for (int i = 0; i <= level; i++) {   //第i行有i+1个数字
                minLen[i] = Math.min(minLen[i], minLen[i + 1]) + triangle.get(level).get(i);
            }
        }
        return minLen[0];
    }

    static int row;

    /**
     * 递归，自顶向下，存在重复计算，超时
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        row = triangle.size();
        return helper2(0, 0, triangle);
    }

    private static int helper2(int level, int index, List<List<Integer>> triangle) {
        // 递归到最后一行结束，向上回朔
        if (level == row - 1) {
            return triangle.get(level).get(index);
        }

        int left = helper2(level + 1, index, triangle);
        int right = helper2(level + 1, index + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(index);
    }

    static Integer[][] memo;

    /**
     * 递归，自顶向下[AC]，记忆化搜索，备忘录算法，将中间结果保存起来方便后面使用
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal3(List<List<Integer>> triangle) {
        row = triangle.size();
        memo = new Integer[row][row];
        return helper3(0, 0, triangle);
    }

    private static int helper3(int level, int index, List<List<Integer>> triangle) {
        if (memo[level][index] != null) {
            return memo[level][index];
        }
        if (level == row - 1) {
            return memo[level][index] = triangle.get(level).get(index);
        }

        int left = helper3(level + 1, index, triangle);
        int right = helper3(level + 1, index + 1, triangle);
        return memo[level][index] = Math.min(left, right) + triangle.get(level).get(index);
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        int res = minimumTotal4(triangle);
        System.out.println(res);
    }

    /**
     * 原地修改
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal4(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j,triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j),triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

}
