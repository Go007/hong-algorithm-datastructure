package com.hong.stacks;

/**
 * @author wanghong
 * @date 2020/03/31 10:33
 * <p>
 * 155. 最小栈 基于链表实现
 **/
public class MinStack2 {

    private Node head;

    private class Node {
        private int value;
        private int min;
        private Node next;

        public Node(int value, int min) {
            this(value, min, null);
        }

        public Node(int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

    /**
     * initialize your data structure here.
     */
    public MinStack2() {

    }

    public void push(int x) {
        head = new Node(x,head == null ? x: Math.min(x,head.min),head);
    }

    public void pop() {
        Node ret = head;
        head = head.next;
        ret.next = null;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
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
