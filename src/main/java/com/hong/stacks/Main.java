package com.hong.stacks;

/**
 * @author wanghong
 * @date 2019/04/06 19:33
 **/
public class Main {
    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
