package com.hong.stacks;


import java.util.Stack;

/**
 * @author wanghong
 * @date 2020/03/31 10:33
 * <p>
 * 有效括号
 * 给定一个只包括 '('，')' 的字符串，判断字符串是否有效
 **/
public class Solution1 {

    /**
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     * @param str
     * @return
     */
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if ('(' == c){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }

                Character top = stack.pop();
                if ('(' != top){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     * @param str
     * @return
     */
    public static boolean isValid2(String str){
        int count = 0;
        for (int i = 0;i < str.length();i++){
            char c = str.charAt(i);
            if ('(' == c){
                count++;
            }else {
                if (count == 0){
                    return false;
                }
                count--;
            }
        }

        return count == 0;
    }

    public static void main(String[] args) {
        String str = "((()))";
        System.out.println(isValid(str));
        System.out.println(isValid2(str));
    }
}
