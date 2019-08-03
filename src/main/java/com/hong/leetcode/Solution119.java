package com.hong.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-08-02 17:45
 * Description:
 * 119. 杨辉三角 II
 */
public class Solution119 {

    /**
     * 空间复杂度 O(k)
     *
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex + 1);

        if (rowIndex == 0) {
            result.add(1);
        } else if (rowIndex == 1) {
            result.add(1);
            result.add(1);
        } else {
            result.add(1);
            result.add(1);
            for (int i = 1; i < rowIndex; i++) {
                result.add(1);
                for (int j = 0; j < i; j++) {
                    result.add(result.get(0) + result.get(1));
                    result.remove(0);
                }
                result.add(1);
                result.remove(0);
            }
        }

        return result;
    }

    /**
     * 获取杨辉三角的指定行
     * 直接使用组合公式C(n,i) = n!/(i!*(n-i)!)
     * 则第(i+1)项是第i项的倍数=(n-i)/(i+1);
     */
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) cur);
            cur = cur * (rowIndex-i)/(i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> result = getRow(3);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}
