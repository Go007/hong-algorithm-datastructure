package com.hong.bst;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Macrowang
 * @date 2019/04/09 17:58
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 * 804 唯一摩尔斯密码词
 **/
public class Solution {

    private static final String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        char[] chars = null;
        StringBuilder code = null;
        char a = 'a';
        int offset = (int) a;
        for (String word : words) {
            chars = word.toCharArray();
            code = new StringBuilder();
            for (char c : chars) {
                code.append(codes[(int) c - offset]);
            }
            set.add(code.toString());
        }

        return set.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(solution.uniqueMorseRepresentations(words));
    }

}
