package com.hong.lfu;

import java.util.HashMap;
import java.util.Map;

public class LFUCache3 {

    private Map<Integer, Node> keyMap;

    private Map<Integer, FreqAggregation> freqMap;

    private int minFreq;

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
        refresh(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        Node node = keyMap.get(key);
        if (node != null) {
            node.value = value;
            refresh(node);
        } else {
            while (keyMap.size() == capacity) {
                reject();
            }
            node = new Node(key, value);
            keyMap.put(key, node);
            addNode(node);
            minFreq = 1;
        }
    }

    private void reject() {
        FreqAggregation minFa = freqMap.get(minFreq);
        keyMap.remove(minFa.head.key);
        minFa = removeNode(minFa.head);
        if (minFa.size == 0) {
            freqMap.remove(minFreq);
        }
    }

    private FreqAggregation addNode(Node node) {
        FreqAggregation fa = freqMap.get(node.freq);
        if (fa == null) {
            fa = new FreqAggregation(node);
            freqMap.put(node.freq, fa);
        } else {
            node.prev = fa.tail;
            fa.tail = fa.tail.next = node;
            fa.size++;
        }
        return fa;
    }

    public void refresh(Node node) {
        FreqAggregation fa = removeNode(node);
        if (fa.size == 0) {
            freqMap.remove(node.freq);
            if (minFreq == node.freq) {
                minFreq = node.freq + 1;
            }
        }
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
        int freq;

        Node prev;
        Node next;

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
