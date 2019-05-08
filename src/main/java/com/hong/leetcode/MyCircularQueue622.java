package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-05-08 17:37
 * Description:
 * 622. 设计循环队列
 */
public class MyCircularQueue622 {

    private int[] data;
    private int front;
    private int rear;
    private int size;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue622(int k) {
        data = new int[k];
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        data[front] = 0;
        front = (front + 1) & data.length;
        size--;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        if (rear < front) {
            // 说明队列循环起来了
            rear = rear == 0 ? data.length - 1 : rear - 1;
            return data[rear];
        }

        if (rear == 0) {
            return data[size - 1];
        }

        return data[rear - 1];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return size == data.length;
    }

    public static void main(String[] args) {
        MyCircularQueue622 circularQueue = new MyCircularQueue622(6);
        System.out.println(circularQueue.enQueue(6));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
    }
}
