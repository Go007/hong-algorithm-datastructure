package com.hong.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-17 14:31
 * Description:
 * 146. LRU缓存机制
 */
public class Solution146 {

    private Map<Integer,Integer> map;
    private List<Integer> list;
    private int size;

    public Solution146(int capacity) {
        this.map = new HashMap<Integer,Integer>();
        this.list = new LinkedList();
        this.size = capacity;
    }

    public Integer get(Integer key){
        Integer value = map.get(key);
        if (value == null){
            return -1;
        }

        list.remove(key);
        list.add(key); // 最近被访问的key被移动到了链表的尾部，链表的头部存放的就是最近最少使用的key
        return value;
    }

    public void put(Integer key,Integer value){

        if (map.get(key) != null){
            map.put(key,value);
            list.remove(key);
            list.add(key);
            return;
        }

        while (map.size() == size){
            Integer deKey = list.remove(0);
            map.remove(deKey);
        }

        map.put(key,value);
        list.add(key);
    }

    public static void main(String[] args) {
        Solution146 cache = new Solution146( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2) );      // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1) );       // 返回 -1 (未找到)
        System.out.println(cache.get(3) );     // 返回  3
        System.out.println(cache.get(4) );       // 返回  4

    }

}
