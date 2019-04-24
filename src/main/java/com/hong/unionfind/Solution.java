package com.hong.unionfind;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author wanghong
 * @date 2019/04/24 20:58
 * Leetcode-547. 朋友圈
 **/
public class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind2 un = new UnionFind2(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    un.unionElements(i, j);
                }
            }
        }

        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(un.find(i));
        }

        return set.size();
    }

    public static void main(String[] args) {
        int[][] M = {{1,1,0},{1,1,0},{0,0,1}};
        Solution solution = new Solution();
        System.out.println(solution.findCircleNum(M));
    }
}
