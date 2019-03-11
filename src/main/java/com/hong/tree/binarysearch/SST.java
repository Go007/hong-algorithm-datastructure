package com.hong.tree.binarysearch;

/**
 * @author Macrowang
 * @date 2019/03/11 19:07
 * 顺序查找表,本质是一个链表
 **/
public class SST<K extends Comparable<K>,V> {

    private class Node{
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private int count;

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    /**
     * 头插法
     * @param key
     * @param value
     */
    public void insert(K key,V value){
        Node node = head;
        while (node != null){
            if (node.key.compareTo(key) == 0){
                node.value = value;
                return;
            }
            node = node.next;
        }

        Node newHead = new Node(key,value);
        newHead.next = head;
        head = newHead;
        count++;
    }

    public boolean contain(K key){
        Node node = head;
        while (node != null){
            if (node.key.compareTo(key) == 0){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public V search(K key){
        Node node = head;
        while (node != null){
            if (node.key.compareTo(key) == 0){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key){
        if (head == null){
            return;
        }

        /**
         *  如果待删除的节点就是头结点, 则需要特殊处理
         *  思考: 对于链表, 可以使用什么技术不去特殊处理头结点的特殊情况?
         */
        if (head.key.compareTo(key) == 0){
            Node delNode = head;
            head = head.next;
            delNode.next = null;
            count--;
            return;
        }

        Node node = head;
        while (node.next != null && node.next.key.compareTo(key) != 0){
            node = node.next;
        }

        if (node.next != null){
            Node delNode = node.next;
            node.next = node.next.next;
            delNode.next = null;
            count--;
        }
    }

}
