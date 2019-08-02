package com.hong.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-08-02 16:58
 * Description:
 * 118. 杨辉三角
 */
public class Solution118 {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        List<Integer> list;
        List<Integer> tmp;
        for (int i = 0; i < numRows; i++) {
            list = new ArrayList<>(i);
            if (i == 0) {
                list.add(1);
            } else if (i == 1) {
                list.add(1);
                list.add(1);
            } else {
                list.add(1);
                tmp = result.get(i - 1);
                for (int j = 1; j < i; j++) {
                    list.add(tmp.get(j - 1) + tmp.get(j));
                }
                list.add(1);
            }
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        for (List<Integer> list : result) {
            for (int i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

}
