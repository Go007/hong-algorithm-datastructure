package com.hong.linked;

import javafx.util.Pair;

/**
 * @author Macrowang
 * @date 2019/04/08 17:03
 * <p>
 * 单链表的递归实现
 **/
public class LinkedListR<E> {

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

    // 在链表的递归实现中，我们不使用虚拟头结点，也能无差异的处理位置0的问题：）
    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        head = add(head, index, e);
        size++;
    }

    /**
     * 向以node为头节点的链表的index位置处插入新节点
     *
     * @param node
     * @param index
     * @param e
     * @return
     */
    private Node add(Node node, int index, E e) {
        // 递归退出条件
        if (index == 0) {
            return new Node(e,node);
        }

        node.next = add(node.next, index - 1, e);
        return node;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 获得链表的第index(0-based)个位置的元素
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        return get(head, index);
    }

    // 在以node为头结点的链表中，找到第index处位置元素，递归算法
    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }

        return get(node.next, index - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Update failed. Illegal index.");

        set(head, index, e);
    }

    /**
     * 更新以node为头节点的链表index位置处的节点数据域
     *
     * @param node
     * @param index
     * @param e
     */
    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    /**
     * 判断以node为头节点的链表中是否包含元素e
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (node.e.equals(e)) {
            return true;
        }

        return contains(node.next, e);
    }


    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }


    /**
     * 删除以node为头节点的链表中index位置的节点，
     * 返回删除后的头节点和被删除节点的数据域
     *
     * @param node
     * @param index
     * @return
     */
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.e);
        }

        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    // 从链表中删除元素e
    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    // 从以node为头结点的链表中，删除元素e，递归算法
    private Node removeElement(Node node, E e) {
        if (node == null)
            return null;
        if (node.e.equals(e)) {
            size--;
            return node.next;
        }
        node.next = removeElement(node.next, e);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListR<Integer> list = new LinkedListR<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }

        System.out.println(list);

        while (!list.isEmpty()) {
            System.out.println("removed " + list.removeLast());
        }

    }
}
