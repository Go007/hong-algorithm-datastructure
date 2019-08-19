package com.hong.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-02 10:48
 * Description:
 */
public class LRUCache2<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    /**
     * accessOrder 表示节点在整个链表中的排列规则，针对get操作
     * true 访问模式， 每次get(key),就把该key对应的Node挪到链表尾部
     * false 插入模式 ，头插法：新插入的元素在头部 尾插法：新插入的元素在尾部
     * @param capacity
     */
    public LRUCache2(int capacity){
        super((int)Math.ceil(capacity / 0.75f) + 1,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

}
