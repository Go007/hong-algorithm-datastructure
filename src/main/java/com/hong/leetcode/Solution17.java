package com.hong.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-05-24 11:09
 * Description:
 * 17. 电话号码的字母组合
 * 回朔算法
 */
public class Solution17 {

    private static final String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 递归深度优先搜索
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        if (digits != null && digits.length() > 0) {
            depthOrder(list, "", digits);
        }

        return list;
    }

    public static void depthOrder(List<String> list, String cur, String digits) {
        if (cur.length() == digits.length()) {
            list.add(cur);
            return;
        }

        int index = digits.charAt(cur.length()) - '0';
        for (int i = 0; i < map[index].length(); i++) {
            depthOrder(list, cur + map[index].charAt(i), digits);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> list = letterCombinations(digits);
        for (String s:list){
            System.out.print(s + ",");
        }
    }

}
