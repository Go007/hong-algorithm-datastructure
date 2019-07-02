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

    public LRUCache2(int capacity){
        super((int)Math.ceil(capacity / 0.75f) + 1,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
