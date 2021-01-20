package com.hong.linked;

/**
 * @author wanghong
 * @desc: 707：设计链表
 * @date 2021/01/21:44
 **/
public class MyLinkedListR {

    private Node head;
    private int size;

    private class Node {
        private int val;
        private Node next;

        public Node() {
            this(-1);
        }

        public Node(int val) {
            this(val, null);
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedListR() {
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        return get(head, index);
    }

    /**
     * 获取以 node 为头节点的链表中 index 位置处的节点值
     *
     * @param node
     * @param index
     * @return
     */
    private int get(Node node, int index) {
        if (index == 0) {
            return node.val;
        }

        return get(node.next, index - 1);
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index < 0) {
            addAtHead(val);
            return;
        }

        head = addAtIndex(head, index, val);
        size++;
    }

    /**
     * 向以 node 为头节点的链表中的 index 位置处添加新节点，值为 val
     * 返回 新增后的头节点
     *
     * @param node
     * @param index
     * @param val
     */
    private Node addAtIndex(Node node, int index, int val) {
        if (index == 0) {
            return new Node(val, node);
        }

        node.next = addAtIndex(node.next, index - 1, val);
        return node;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        head = deleteAtIndex(head, index);
        size--;
    }

    /**
     * 删除以 node 为头节点的链表中 index 位置处的节点，返回删除后的头节点
     *
     * @param node
     * @param index
     * @return
     */
    private Node deleteAtIndex(Node node, int index) {
        if (index == 0) {
            Node n = node.next;
            node.next = null;
            return n;
        }

        node.next = deleteAtIndex(node.next, index - 1);
        return node;
    }

    public static void main(String[] args) {
        MyLinkedListR l = new MyLinkedListR();
        l.addAtHead(7);
        l.addAtHead(2);
        l.addAtHead(1);
        l.addAtIndex(3, 0);
        l.deleteAtIndex(2);
        l.addAtHead(6);
        l.addAtTail(4);
        System.out.println(l.get(4));
        l.addAtHead(4);
        l.addAtIndex(5, 0);
        l.addAtHead(6);
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
