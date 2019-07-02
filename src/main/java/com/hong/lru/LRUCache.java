package com.hong.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-01 17:21
 * Description:
 * LRU cache算法实现
 */
public class LRUCache<K,V> {


    private LRUNode<K,V> head;
    private LRUNode<K,V> tail;

    private int capacity;

    private Map<K,LRUNode<K,V>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public V get(K key){
        LRUNode<K,V> node = map.get(key);
        if (node == null){
            return null;
        }

        // 调整node到尾部
        refresh(node);
        return node.value;
    }

    public void set(K key,V value){
        LRUNode<K,V> node = map.get(key);
        if (node == null){
            while (map.size() >= capacity){
                K oldKey = remove(head);
                map.remove(oldKey);
            }

            node = new LRUNode<>(key,value);
            add(node);
            map.put(key,node);
        }else {
            // 更新节点并调整到尾部
            node.value = value;
            refresh(node);
        }
    }

    private void refresh(LRUNode<K,V> node){
        // 如果访问的是尾节点，无需移动节点
        if (node == tail){
            return;
        }

        // 把节点移动到尾部
        remove(node);
        add(node);
    }

    private K remove(LRUNode<K,V> node){
        if (node == tail){
            tail = tail.pre;
        }else if (node == head){
            head = head.next;
        }else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        return node.key;
    }

    private void add(LRUNode<K,V> node){
        if (tail != null){
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if (head == null){
            head = tail;
        }
    }
}
