package com.hong.lru;

/**
 * Created by wanghong
 * Date 2019-07-01 17:26
 * Description:
 */
public class LRUNode<K,V> {

    public K key;
    public V value;
    public LRUNode pre;
    public LRUNode next;

    public LRUNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
