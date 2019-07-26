package com.hong.leetcode;

import java.util.Stack;

/**
 * Created by wanghong
 * Date 2019-07-26 09:24
 * Description:
 * 232. 用栈实现队列
 * <p>
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 */
public class Solution232 {

    /**
     * 反转元素的入队顺序,栈顶相当于队列的front
     */
    private Stack<Integer> s0;

    /**
     * 存储元素的最终顺序，栈顶相当于队列的rear
     */
    private Stack<Integer> s1;

    /**
     * Initialize your data structure here.
     */
    public Solution232() {
        s0 = new Stack<>();
        s1 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        s1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (s0.isEmpty()){
            while (!s1.isEmpty()){
                s0.push(s1.pop());
            }
        }

        return s0.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (s0.isEmpty()){
            while (!s1.isEmpty()){
                s0.push(s1.pop());
            }
        }

        return s0.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return s0.isEmpty() && s1.isEmpty();
    }

    public static void main(String[] args) {
        Solution232 queue = new Solution232();

        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());  // 返回 1
        queue.empty(); // 返回 false
    }
}
