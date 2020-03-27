package com.hong.queues;

import java.util.Arrays;

/**
 * @author wanghong
 * @date 2020/03/27 16:55
 * 固定大小的循环队列
 **/
public class FixedLoopQueue<E> {

    private E[] data;
    // front 指向头元素，tail指向尾元素
    private int front,tail;
    // 队列固定容量
    private int capacity;
    // 队列当前元素个数
    private int size;

    public FixedLoopQueue(){
        this(10);
    }

    public FixedLoopQueue(int capacity){
        this.capacity = capacity;
        data = (E[])new Object[capacity];
    }

    /**
     * 获取当前队列元素个数
     * @return
     */
    public int size(){
         return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean isFull(){
        return size() == capacity;
    }

    public void enqueue(E e){
        if (isFull()){
            throw new IllegalStateException("The queue is full");
        }

        data[tail] = e;
        tail = (tail + 1) % capacity;
        size++;
    }

    public E dequeue(){
        if (isEmpty()){
            throw new IllegalStateException("The queue is empty");
        }

        E e = data[front];
        front = (front + 1)%capacity;
        size--;
        return e;
    }

    @Override
    public String toString() {
        return "FixedLoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        FixedLoopQueue<Integer> queue = new FixedLoopQueue<>(10);
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
