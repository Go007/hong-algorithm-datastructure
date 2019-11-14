package com.hong.leetcode;

import java.util.*;

/**
 * Created by wanghong
 * Date 2019-11-14 09:41
 * Description:454. 四数相加 II
 */
public class Solution454 {

    /**
     * 暴力破解法 超时
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = A.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    for (int l = 0; l < len; l++) {
                        if (A[i] + B[j] + C[k] + D[l] == 0) {
                            ans.add(Arrays.asList(i, j, k, l));
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        int size = fourSumCount2(A, B, C, D);
        System.out.println(size);
    }

    /**
     * HASH
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int len = A.length;
        int count = 0;

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j=0;j < len;j++){
                map.put(A[i] + B [j],map.getOrDefault(A[i] + B[j],0) + 1);
            }
        }

        for (int k = 0;k < len;k++){
            for (int l = 0;l < len;l++){
                if (map.containsKey(-(C[k] + D[l]))){
                    count += map.get(-(C[k] + D[l]));
                }
            }
        }

        return count;
    }
}
