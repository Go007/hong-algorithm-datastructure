package com.hong.queues;

import java.util.Arrays;

/**
 * @author wanghong
 * @date 2020/03/12 20:54
 * 基于数组实现的大小固定的简单队列，不支持动态扩/缩容
 **/
public class SimpleArrayQueue<T> {
    /**
     * 队列容器
     */
    private T[] data;

    /**
     * 队列中元素个数
     */
    private int size;

    /**
     * 队列大小
     */
    private int capacity;

    public SimpleArrayQueue(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
    }

    /**
     * 元素入队
     *
     * @param t
     */
    public void enqueue(T t) {
        if (size + 1 > capacity) {
            throw new IllegalArgumentException("The queue capacity has full.");
        }
        // 将元素添加到数组的末端
        data[size] = t;
        size++;
    }

    /**
     * 队首元素出队
     *
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T t = data[0];
        // 后一个位置的元素依次覆盖前一个
        for (int i = 1; i < size; i++) {
            data[i-1] = data[i];
        }

        size--;
        data[size] = null;
        return t;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size(){
        return this.size;
    }

    /**
     * 查看队首元素
     * @return
     */
    public T getFront(){
        return data[0];
    }

    @Override
    public String toString() {
        return "SimpleArrayQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        SimpleArrayQueue<Integer> queue = new SimpleArrayQueue<>(10);
        for(int i = 1 ; i <= 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            // 使用位运算判断一个数的奇偶性
            if ((i & 1) == 1){
                System.out.println(i + "->" + queue.dequeue());
            }
        }
    }

}
