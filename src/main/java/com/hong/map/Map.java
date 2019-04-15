package com.hong.map;

/**
 * @author wanghong
 * @date 2019/04/15 22:43
 * 映射
 **/
public interface Map<K,V> {
    void add(K key,V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key,V value);
    int getSize();
    boolean isEmpty();
}
