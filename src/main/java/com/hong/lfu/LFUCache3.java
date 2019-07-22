package com.hong.lfu;

import java.util.HashMap;
import java.util.Map;

public class LFUCache3 {

    private Map<Integer, Node> keyMap;

    private Map<Integer, FreqAggregation> freqMap;

    private int minFreq = -1;

    private int capacity;

    public LFUCache3(int capacity) {
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }

        Node node = keyMap.get(key);
        refreshNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        Node node = keyMap.get(key);
        if (node != null) {
            node.value = value;
            // 从原来的频次链表中移除
            removeNode(node);
            node.freq++;
            // 加入频率自增后新的链表中
            addNode(node);
        } else {
            // 检查容量是否满了
            while (keyMap.size() == capacity) {
                // 删除 频次最小的链表中的头节点
                FreqAggregation minFa = freqMap.get(minFreq);
                Node delNode = minFa.head;
                removeNode(minFa.head);
                keyMap.remove(delNode.key);
            }
            node = new Node(key, value);
            keyMap.put(key, node);
            addNode(node);
        }
    }

    private FreqAggregation addNode(Node node) {
        FreqAggregation fa = freqMap.get(node.freq);

        if (fa == null) {
            fa = new FreqAggregation(node);
            freqMap.put(node.freq, fa);
        } else {
            fa.tail = fa.tail.next = node;
            fa.size++;
        }

        return fa;
    }

    /**
     * 当get(key)或put(key)时，刷新node的频次
     *
     * @param node
     */
    public void refreshGet(Node node) {
        removeNode(node);
        node.freq++;
        addNode(node);
    }

    private FreqAggregation removeNode(Node node) {
        if (node == null) {
            return null;
        }

        FreqAggregation fa = freqMap.get(node.freq);
        if (node == fa.head) {
            fa.head = fa.head.next;
            if (fa.head == null) {
                fa.tail = null;
            }
        } else if (node == fa.tail) {
            fa.tail = fa.tail.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }

        fa.size--;

        return fa;
    }

    private static class FreqAggregation {
        Node head;
        Node tail;
        int size;

        public FreqAggregation(Node node) {
            head = tail = node;
            size++;
        }
    }

    private static class Node {
        int key;
        int value;
        int freq; // 访问频次,初始化为1

        Node prev;// 前驱节点
        Node next;// 后继节点

        Node(int key, int value) {
            this(key, value, 1);
        }

        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }


}
