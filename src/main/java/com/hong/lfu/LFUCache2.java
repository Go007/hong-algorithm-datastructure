package com.hong.lfu;

import java.util.HashMap;
import java.util.List;
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
    private Map<K, Node> keyMap;

    /**
     * 将频次看成优先级， 频次 --》 拥有该频次的Node链表，三个元素：链表头节点，链表尾节点，链表长度
     */
    private Map<Integer, FreqAggregation> freqMap;

    /**
     * 当前最小频次
     */
    private int minFreq;

    /**
     * 容量
     */
    private int capacity;

    public LFUCache2(int capacity) {
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        Node node = keyMap.get(key);
        FreqAggregation fa = freqMap.get(node.freq);

        if (node != null) {
            node.value = value;

        }else {
            node = new Node(key, value);
            if (fa == null){
                fa = new FreqAggregation(node);
            }else{

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
        List<Node> nodeList;
        /**
         * 假设现在:
         * capacity = 3
         * 频次链表如下：
         * 频次          Node聚集
         * 1           [n0]   FreqAggregation1
         * 2           [n2,n3]   FreqAggregation2
         * <p>
         * 则FreqAggregation1.next = FreqAggregation2
         * minFreq = 1
         * <p>
         * put()操作，插入n4，判断key是否已经存在：
         * 1.存在更新value,
         * 由n4.freq 找到 fa4,将n4从fa4中删除：
         * 如果删除后 size == 0，fa4.prev.next = fa4.next; fa4.next.prev = fa4.prev;
         * 从map中删除fa4，minFreq = fa4.prev.head.freq;
         * n4.freq++ ,找fa5:
         * 找到了，则将 fa5.head = n4；
         * 没有找到，则新建fa5，放入map中；
         * if minFreq > n4.freq,则 minFreq ==  n4.freq; fa5.next = minFreq对应的fa0；
         * <p>
         * <p>
         * 2.不存在,发现 keyMap.size == capacity,触发执行淘汰策略。
         * 根据 minFreq = 1，找到 FreqAggregation1，删除头节点，size--变为0；
         * 插入n4，
         * <p>
         * 再由next = FreqAggregation2，将minFreq更新为 FreqAggregation2.head.freq,
         * 同时删除 FreqAggregation1。
         */
        FreqAggregation prev;
        FreqAggregation next;

        public FreqAggregation(Node node){
            head = tail = node;
            size++;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        int freq; // 访问频次

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
}
