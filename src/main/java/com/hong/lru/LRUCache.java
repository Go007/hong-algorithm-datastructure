package com.hong.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-07-01 17:21
 * Description:
 * LRU cache算法实现  最近最少使用(Least Recently Used)
 * 核心思想：根据访问时间淘汰key，把数据加入一个链表中，按访问时间排序，发生淘汰的时候，把访问时间最旧的淘汰掉。
 * 缺点：表面功夫做得好的人可以逃过优化，即LRU算法只关注了最近的时刻key的使用情况，却没有对key平时的活跃度进行考量
 * 比如：某个平常一直很少访问的key，在内存满了，触发执行淘汰策略之前，刚好被访问到了，
 *  那么这个key就逃过了此次的淘汰，而实际上它是应该在淘汰范围内的。
 *
 *  Redis中的LRU并不是完全按照标准的做法来的，为了达到O(1)的时间复杂度，空间换时间，
 *  牺牲较大的内存存储空间，不划算。所以Redis采用了一个近似的做法，就是随机取出若干个key，
 *  然后按照访问时间排序后，淘汰掉最不经常使用的。
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

    public void put(K key,V value){
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

    public static void main(String[] args) {
        LRUCache3 cache = new LRUCache3( 2 /* 缓存容量 */ );

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
