package com.hong.queues;

/**
 * @author wanghong
 * @date 2020/10/21 0:06
 **/
public class MyCircularQueue2 {

    private int[] data;
    private int front;
    private int tail;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue2(int k) {
        if (k == 0) {
            throw new IllegalArgumentException("k must be > 0");
        }
        data = new int[k + 1]; // 这里浪费一个空间用于表示最后一个元素的下一个空位
    }

    public int size() {
        return tail >= front ? tail - front : data.length + tail - front;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        data[tail] = value;
        tail = (tail + 1) % data.length;
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
        front = (front + 1) % data.length;

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

        // return data[(front + size() - 1) % data.length];
        return tail == 0 ? data[data.length - 1] : data[tail - 1];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        //return size() == 0;
        return tail == front;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        //return size() == data.length - 1;
        return (tail + 1) % data.length == front;
    }

    public static void main(String[] args) {
        MyCircularQueue2 q = new MyCircularQueue2(3);
        System.out.println(q.enQueue(1));
        System.out.println(q.enQueue(2));
        System.out.println(q.enQueue(3));
        System.out.println(q.enQueue(4));
        System.out.println(q.Rear());
    }
}
