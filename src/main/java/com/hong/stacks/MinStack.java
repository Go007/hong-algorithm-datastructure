package com.hong.stacks;

import java.util.Stack;

/**
 * Created by wanghong
 * Date 2019-07-24 16:32
 * Description:
 * 155. 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/min-stack
 */
public class MinStack {

    /** 思路：每次入栈2个元素，一个是入栈的元素本身，一个是当前栈元素的最小值
     ** 如：入栈序列为2-3-1，则入栈后栈中元素序列为：2-2-3-2-1-1
     ** 用空间换取时间
     **/

    private java.util.Stack<Integer> stack;


    /** initialize your data structure here. */
    public MinStack() {
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
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
