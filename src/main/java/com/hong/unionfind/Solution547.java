package com.hong.unionfind;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author wanghong
 * @date 2019/04/24 20:58
 * Leetcode-547. 朋友圈
 **/
public class Solution547 {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind6 un = new UnionFind6(n);
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
        Solution547 solution = new Solution547();
        System.out.println(solution.findCircleNum(M));
    }
}
