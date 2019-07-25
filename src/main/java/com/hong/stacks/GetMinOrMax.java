package com.hong.stacks;

import java.util.Stack;

/**
 * @author wanghong
 * @date 2019/07/20 17:45
 **/
public class GetMinOrMax<E extends Comparable<E>> {
    /**
     * java实现栈-输出最大值，最小值，时间复杂度O(1)
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的getMin函数。
     * 在该栈中，调用getMin、push及pop的时间复杂度都是O(1).
     * <p>
     * 最小值思路：用一个辅助栈stack2记住每次入栈stack1的当前最小值:在stack1入栈时，往stack2中加入当前最小值；
     * stack1元素出栈时，stack2也出栈一个元素。最小值从stack2中获取及栈顶元素。
     *
     * 空间换时间
     */
    Stack<E> originStack = new Stack<>();
    Stack<E> minStack = new Stack<>();
    Stack<E> maxStack = new Stack<>();

    /**
     * 入栈
     *
     * @param e
     */
    public void push(E e) {
        originStack.push(e);

        if (minStack.isEmpty() || e.compareTo(minStack.peek()) < 0) {
            minStack.push(e);
        }else if (e.compareTo(minStack.peek()) > 0){
            minStack.push(minStack.peek());
        }

        if (maxStack.isEmpty() || e.compareTo(maxStack.peek()) > 0){
            maxStack.push(e);
        }else if (e.compareTo(maxStack.peek()) < 0){
            maxStack.push(maxStack.peek());
        }
    }

    /**
     * 出栈
     *
     * @return
     */
    public E pop() {
        if (!originStack.isEmpty() && !minStack.isEmpty() && !maxStack.isEmpty()){
            E e = originStack.pop();
            minStack.pop();
            maxStack.pop();
            return e;
        }else {
            throw new IllegalStateException("stack is empty");
        }
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public E peek() {
        return originStack.peek();
    }

    public E getMin() {
        return minStack.peek();
    }

    public E getMax() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        GetMinOrMax<Integer> getMinOrMax = new GetMinOrMax<>();
        getMinOrMax.push(1);
        getMinOrMax.push(1);
        getMinOrMax.push(3);
        getMinOrMax.push(2);
        getMinOrMax.push(4);
        System.out.println(getMinOrMax.getMin());
        System.out.println(getMinOrMax.getMax());
        getMinOrMax.pop();
        System.out.println(getMinOrMax.getMin());
        System.out.println(getMinOrMax.getMax());
    }

}
