package com.hong.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wanghong
 * @desc: LeetCode146. LRU 缓存
 * @date 2022/06/18:10
 **/
public class LRUCache5 {
    private Map<Integer,Integer> containerMap;

    private List<Integer> keyList;

    private int size;

    public LRUCache5(int capacity) {
        this.containerMap = new HashMap();
        this.keyList = new LinkedList();
        this.size = capacity;
    }

    public int get(int key) {
        Integer objKey = Integer.valueOf(key);
        Integer value = containerMap.get(objKey);
        if (value == null){
            return -1;
        }

        keyList.remove(objKey);
        keyList.add(objKey);
        return value;
    }

    public void put(int key, int value) {
        Integer objKey = Integer.valueOf(key);
        if (containerMap.containsKey(objKey)){
            containerMap.put(objKey,value);
            keyList.remove(objKey);
            keyList.add(objKey);
            return;
        }

        while(containerMap.size() == size){
            containerMap.remove(keyList.remove(0));
        }

        containerMap.put(objKey,value);
        keyList.add(objKey);
    }

    public static void main(String[] args) {
        LRUCache5 cache = new LRUCache5(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
    }
}
