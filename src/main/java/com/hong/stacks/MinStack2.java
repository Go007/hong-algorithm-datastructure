package com.hong.stacks;

import java.util.Stack;

/**
 * Created by wanghong
 * Date 2019-07-24 18:02
 * Description:
 */
public class MinStack2 {

    /** 思路：每次入栈2个元素，一个是入栈的元素本身，一个是当前栈元素的最小值
     ** 如：入栈序列为2-3-1，则入栈后栈中元素序列为：2-2-3-2-1-1
     ** 用空间代价来换取时间代价
     **/

    private Stack<Integer> stack;


    /** initialize your data structure here. */
    public MinStack2() {
        stack = new Stack();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(x);
            stack.push(x);
        }else {
            int peek = stack.peek();
            stack.push(x);
            if (peek < x){
                stack.push(peek);
            }else {
                stack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        return stack.get(stack.size() - 2);
    }

    public int getMin() {
        return stack.peek();
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}
