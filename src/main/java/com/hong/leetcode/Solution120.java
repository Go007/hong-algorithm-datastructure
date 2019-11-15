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
     * 原地修改
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal4(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * 二维数组
     * 这是二维dp问题
     * 思考问题：从下到上，从上到下，这两种思维方式一样么？怎么选可以让递推更容易，更少的考虑边界条件？
     * 状态：
     * dp[i][j]表示第i行，第j列的最优解
     * 思考：
     * 先考虑从上向下递推，自己画图自己走一遍
     * 原三角形，设置一个最优解三角形
     * 需要画出最优解三角形，最优解替换最后一层，再递推下去，最左侧与最右侧的路径是固定的，其余位置要判断
     * 实际证明，下一层位置，只能从上一层某两个位置，到达
     * 从下往上考虑同样要画出三角形
     * 结论：从上到下思考，要考虑最左侧，最右侧特殊情况，而从下向上思考无需考虑特殊情况
     * 算法：
     * 1，设置一个二维数组，最优值三角形dp[][]，初始化为0，dp[i][j]代表了当前点最优解
     * 2，从三角形低向上进行dp
     * a，边界：三角形最下一层就是最优值
     * b，利用i循环，从倒数第二层递推到最上面一层，对于每层各列，进行dp
     * 对于第i行，第j列最优解dp[i][j]，前一个状态的两个位置的最优解为dp[i+1][j],dp[i+1][j+1]
     * 递推式dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
     * 3,返回dp[0][0]
     * @param triangle
     * @return
     */
    public static int minimumTotal5(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }

        // + 1 就不用初始化最后一层
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        List<Integer> curList = null;
        for (int i = triangle.size()-1;i >= 0;i--){
            curList = triangle.get(i);
            for (int j = 0;j < curList.size();j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + curList.get(j);
            }
        }

        return dp[0][0];
    }

    /**
     * 自底向上, DP 【AC】用迭代    一维数组
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        // 只需要记录每一层的最小值即可
        int[] minLen = new int[row + 1];
        for (int level = row - 1; level >= 0; level--) {
            for (int i = 0; i <= level; i++) {   //第i行有i+1个数字
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
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

    /**
     *
     * @param triangle
     * @return
     */
    public int minimumTotal6(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] cache = new int[size][size];
        return recur(triangle, size, 0, 0, cache);
    }

    private int recur(List<List<Integer>> triangle, int size, int row, int col, int[][] cache) {
        if (cache[row][col] != 0) return cache[row][col];
        else if (row == size - 1) return cache[row][col] = triangle.get(row).get(col);
        int left = recur(triangle, size, row + 1, col, cache);
        int right = recur(triangle, size, row + 1, col + 1, cache);
        return cache[row][col] = Math.min(left, right) + triangle.get(row).get(col);
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        int res = minimumTotal5(triangle);
        System.out.println(res);
    }

}
