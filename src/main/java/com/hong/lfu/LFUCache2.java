package com.hong.lfu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-18 15:03
 * Description:
 */
public class LFUCache2<K, V> {

    /**
     * 储存key --》Node 映射的容器
     */
    private Map<K, Node<K, V>> keyMap;

    /**
     * 将频次看成优先级， 频次 --》 拥有该频次的Node链表，三个元素：链表头节点，链表尾节点，链表长度
     */
    private Map<Integer, FreqAggregation> freqMap;

    /**
     * 当前最小频次
     */
    private int minFreq = -1;

    /**
     * 容量
     */
    private int capacity;

    public LFUCache2(int capacity) {
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!keyMap.containsKey(key)) {
            return null;
        }

        Node<K, V> node = keyMap.get(key);
        refreshNode(node);
        return node.value;
    }

    public void put(K key, V value) {
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

    private void addNode(Node node) {
        FreqAggregation fa = freqMap.get(node.freq);
        if (fa == null) {
            fa = new FreqAggregation(node);
            freqMap.put(node.freq, fa);
        } else {
            fa.tail = fa.tail.next = node;
            fa.size++;
        }

        // 更新最小频次
        minFreq = Math.min(minFreq == -1 ? 1 : minFreq, node.freq);
    }

    /**
     * 当get(key)或put(key)时，刷新node的频次
     *
     * @param node
     */
    public void refreshNode(Node<K, V> node) {
        removeNode(node);
        node.freq++;
        addNode(node);
    }

    /**
     * 从node对应的频次链表中移除node
     *
     * @param node
     */
    private void removeNode(Node<K, V> node) {
        if (node == null) {
            return;
        }

        FreqAggregation fa = freqMap.get(node.freq);
        if (node == fa.head) {
            fa.head = node.next;
            node.next = null;
        } else if (node == fa.tail) {
            fa.tail = node.prev;
            node.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }

        fa.size--;
        if (fa.size == 0) {
            freqMap.remove(node.freq);
            if (freqMap.size() == 0) {
                minFreq = -1;
            } else if (minFreq == node.freq) {
                ArrayList<Integer> freqList = new ArrayList<>(freqMap.keySet());
                Collections.sort(freqList);
                minFreq = freqList.get(0);
            }
        }
    }

    /**
     * 同频次Node聚集
     */
    private static class FreqAggregation {
        Node head;
        Node tail;
        int size;

        public FreqAggregation(Node node) {
            head = tail = node;
            size++;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        int freq; // 访问频次,初始化为1

        Node prev;// 前驱节点
        Node next;// 后继节点

        Node(K key, V value) {
            this(key, value, 1);
        }

        Node(K key, V value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        String[] operation = {"LFUCache", "put", "get", "put", "get", "put","get"};
        int[][] data = {{2}, {1, 2}, {1}, {3}, {2,3}, {4},{1,3,4}};

        LFUCache2<Integer,Integer> cache = new LFUCache2(data[0][0]);

        for (int i = 1; i < operation.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if ("put".equals(operation[i])) {
                    cache.put(data[i][j],data[i][j]);
                }else {
                    System.out.println(cache.get(data[i][j]));
                }
            }
        }

        /*  LFUCache2 cache = new LFUCache2(2 *//* capacity (缓存容量) *//*);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));      // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));// 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));     // 返回 3
        System.out.println(cache.get(4));     // 返回 4*/
    }
}
