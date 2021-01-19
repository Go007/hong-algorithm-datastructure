package com.hong.linked;

/**
 * @author wanghong
 * @desc: 707：设计链表
 * @date 2021/01/21:44
 **/
public class MyLinkedList {

    private Node dummyHead;
    private Node tail;
    private int size;

    private class Node {
        private int val;
        private Node next;

        public Node() {
            this(0);
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
    public MyLinkedList() {
        dummyHead = new Node(-1);
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }

        Node node = dummyHead.next;
        while (index-- > 0) {
            node = node.next;
        }
        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node head = new Node(val, dummyHead.next);
        dummyHead.next = head;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (tail == null) {
            dummyHead.next = tail = node;
        } else {
            tail = tail.next = node;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        if (index < 0) {
            addAtHead(val);
            return;
        }

        Node pre = dummyHead;
        while (index-- > 0) {
            pre = pre.next;
        }

        Node next = pre.next;
        pre.next = new Node(val, next);
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }

        Node pre = dummyHead;
        while (index-- > 0) {
            pre = pre.next;
        }

        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.addAtHead(9);
        System.out.println(l.get(1));
        l.addAtIndex(1,1);
        l.addAtIndex(1,7);
        l.deleteAtIndex(1);
        l.addAtHead(7);
        l.addAtHead(4);
        l.deleteAtIndex(1);
        l.addAtIndex(1,4);
        l.addAtHead(2);
        l.deleteAtIndex(5);

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
