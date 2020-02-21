package com.hong.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-02 10:48
 * Description: 基于LinkedHashMap构建LRU
 */
public class LRUCache2<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    /**
     * accessOrder 表示节点在整个链表中的排列规则，针对get操作
     * true 访问模式， 每次get(key),就把该key对应的Node挪到链表尾部
     * false 插入模式 ，头插法：新插入的元素在头部 尾插法：新插入的元素在尾部
     * @param capacity
     */
    public LRUCache2(int capacity){ //  // 这里就是传递进来最多能缓存多少数据
        //这块就是设置一个hashmap的初始大小，同时最后一个true指的是让linkedhashmap按照访问顺序来进行排序，
        // 最近访问的放在头，最老访问的就在尾
        super((int)Math.ceil(capacity / 0.75f) + 1,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //说当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size() > capacity;
    }

    public static void main(String[] args) {
        System.out.println(23*0.75f);
        int cap = 23;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }

}
