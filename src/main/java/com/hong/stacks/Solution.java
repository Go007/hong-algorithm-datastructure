package com.hong.stacks;

import java.util.Stack;

/**
 * @author wanghong
 * @date 2019/04/06 22:02
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串
 **/
public class Solution {

    /**
     * 思路：将目标字符串s中的字符依次压栈，在压栈的过程中，
     * 如果碰到要压入栈的字符是右边符号(')'，'}'，']')，
     * 则取出栈顶元素，进行匹配。
     * 栈顶元素反映了在嵌套的层次关系中，最近的需要匹配的元素
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character topChar = stack.pop();
                if (c == ')' && topChar != '('){
                    return false;
                }
                if (c == '}' && topChar != '{'){
                    return false;
                }
                if (c == ']' && topChar != '['){
                    return false;
                }
            }
        }

        // 最后，stack中没有元素，则说明全部匹配
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("([)]"));
    }

}
