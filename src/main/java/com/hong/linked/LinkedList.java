package com.hong.linked;

/**
 * @author wanghong
 * @date 2019/04/07 22:12
 **/
public class LinkedList<E> {

    private class Node {
        private E e;
        private Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表中元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 头插法
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 尾插法
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在链表的index位置添加新元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        // 判断index是否合法
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        if (index == 0) {
            head = new Node(e, head);
        } else {
            // 遍历链表，找出index位置的前一个节点
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

          /*  Node node = new Node(e,prev.next);
            prev.next = node;*/
            prev.next = new Node(e, prev.next);
        }
        size++;
    }
}