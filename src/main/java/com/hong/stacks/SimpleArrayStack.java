package com.hong.stacks;

import java.util.Arrays;

/**
 * @author wanghong
 * @date 2020/03/12 21:33
 * 基于数组实现的大小固定的简单栈，不支持动态扩/缩容
 **/
public class SimpleArrayStack<T> {

    private T[] data;

    private int size;

    private int capacity;

    public SimpleArrayStack(int capacity){
        data = (T[])new Object[capacity];
        this.capacity = capacity;
    }

    public boolean isEmpty(){
        return size <= 0;
    }

    public int size(){
        return size;
    }

    /**
     * 入栈
     * @param t
     */
    public void push(T t){
        // 栈满
        if (size + 1 > capacity){
            throw new IllegalArgumentException("The stack is full.");
        }

        data[size] = t;
        size++;
    }

    /**
     * 出栈
     * @return
     */
    public T pop(){
        if (isEmpty()){
            return null;
        }

        T t = data[size-1];
        size--;
        data[size] = null;
        return t;
    }

    public T peek(){
        if (isEmpty()){
            return null;
        }

        return data[size-1];
    }

    @Override
    public String toString() {
        return "SimpleArrayStack{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        SimpleArrayStack<Integer> stack = new SimpleArrayStack<>(10);
        for(int i = 1 ; i <= 10 ; i ++){
            stack.push(i);
            System.out.println(stack);
            // 奇数出栈,使用位运算判断一个数的奇偶性
            if ((i & 1) == 1){
                System.out.println(i + "->" + stack.pop());
            }
        }
    }
}
