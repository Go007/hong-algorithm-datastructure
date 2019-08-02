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
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>(rowIndex+1);

        if (rowIndex == 0) {
            result.add(1);
        } else if (rowIndex == 1) {
            result.add(1);
            result.add(1);
        } else {
            result.set(0,1);
            for (int i = 1; i < rowIndex; i++) {
                result.set(i,result.)
            }
            result.set(rowIndex,1);
        }

        return
    }

}
