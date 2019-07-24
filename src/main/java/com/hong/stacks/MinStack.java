package com.hong.stacks;

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

    private class Node {
        private int e;
        private Node next;

        public Node(int e) {
            this(e, null);
        }

        public Node(int e, Node next) {
            this.e = e;
            this.next = next;
        }

    }

    private Node dummyNode;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        dummyNode = new Node(-1);
    }

    public void push(int x) {
        dummyNode.next = new Node(x, dummyNode.next);
    }

    public void pop() {
        if (dummyNode.next != null) {
            Node head = dummyNode.next;
            dummyNode.next = head.next;
            head.next = null;
        }
    }

    public int top() {
        return dummyNode.next == null ? -1 : dummyNode.next.e;
    }

    public int getMin() {
        return -1;
    }

}
