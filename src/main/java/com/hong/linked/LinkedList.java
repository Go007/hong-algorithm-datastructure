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

    //  private Node head;
    // 为链表设立虚拟头节点
    private Node dummyNode;
    private int size;

    public LinkedList() {
        // head = null;
        dummyNode = new Node();
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
  /*  public void add(int index, E e) {
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

          *//*  Node node = new Node(e,prev.next);
            prev.next = node;*//*
            prev.next = new Node(e, prev.next);
        }
        size++;
    }*/

    /**
     * 使用虚拟头节点，从而避免原先的需要对index=0的特殊处理
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        // 判断index是否合法
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        // 遍历链表，找出index位置的前一个节点
        Node prev = dummyNode;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 获取链表index位置的Node
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyNode.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 更新链表index位置的节点数据域
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyNode.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 判断链表中是否含有元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyNode.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
      /*  Node cur = dummyNode.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }*/
        for (Node cur = dummyNode.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
    }
}