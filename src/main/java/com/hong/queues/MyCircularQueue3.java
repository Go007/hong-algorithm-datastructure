package com.hong.queues;

/**
 * @author wanghong
 * @date 2020/10/21 21:51
 **/
public class MyCircularQueue3 {
    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    private class Node {
        public int e;
        public Node next;

        public Node() {
            this(0);
        }

        public Node(int e) {
            this(e, null);
        }

        public Node(int e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue3(int k) {
        this.capacity = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        if (tail == null) {
            head = tail = new Node(value);
        } else {
            tail = tail.next = new Node(value);
        }
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

        Node retNode = head;
        head = head.next;
        retNode.next = null;

        if (head == null) {
            tail = null;
        }
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
        return head.e;
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return tail.e;
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
        return size == capacity;
    }

    public static void main(String[] args) {
        MyCircularQueue3 q = new MyCircularQueue3(8);
        System.out.println(q.enQueue(3));
        System.out.println(q.enQueue(9));
        System.out.println(q.enQueue(5));
        System.out.println(q.enQueue(0));
        System.out.println(q.deQueue());
        System.out.println(q.deQueue());
        System.out.println(q.isEmpty());
        System.out.println(q.isEmpty());
        System.out.println(q.Rear());
        System.out.println(q.Rear());
        System.out.println(q.deQueue());
    }
}
