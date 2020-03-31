package com.hong.queues;

/**
 * @author wanghong
 * @date 2020/03/27 16:55
 * 固定大小的循环队列
 * 622. 设计循环队列
 **/
public class MyCircularQueue {

    private int[] data;
    private int size;
    private int front;
    private int capacity;


    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.capacity = k;
        data = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()){
            return false;
        }

        //data[(front + size) % capacity] = value;
        //size++;
        // 调整为下面，保持计算 tail 的方式统一
        size++;
        data[(front + size - 1) % capacity] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }
        data[front] = 0;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return data[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return data[(front + size - 1) % capacity];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == capacity;
    }
}
