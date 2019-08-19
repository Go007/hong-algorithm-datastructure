package com.hong.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong
 * Date 2019-08-19 10:40
 * Description:
 */
public class LRUCache3<K, V> {

    private LRUNode<K, V> head;
    private LRUNode<K, V> tail;
    private int capacity;
    private Map<K, LRUNode<K, V>> container;

    public LRUCache3(int capacity) {
        this.capacity = capacity;
        container = new HashMap<>();
    }

    public V get(K key) {
        LRUNode<K, V> node = container.get(key);
        if (node == null) {
            return null;
        }
        afterNodeAccess(node);
        return node.value;
    }

    public void put(K key, V value) {
        LRUNode<K, V> node = container.get(key);
        if (node != null) {
            node.value = value;
            afterNodeAccess(node);
        } else {
            // 尾插法
            linkLast(key, value);
            afterNodeInsertion();
        }
    }

    private void linkLast(K key, V value) {
        LRUNode<K, V> p = new LRUNode<>(key, value);
        LRUNode<K, V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.pre = last;
            last.next = p;
        }
        container.put(key, p);
    }

    /**
     * move node to last
     *
     * @param e
     */
    private void afterNodeAccess(LRUNode<K, V> e) {
        LRUNode<K, V> last;
        if ((last = tail) != e) {
            LRUNode<K, V> p = e, b = p.pre, a = p.next;
            p.next = null;
            if (b == null)
                head = a;
            else
                b.next = a;
            if (a != null)
                a.pre = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.pre = last;
                last.next = p;
            }
            tail = p;
        }
    }

    /**
     * possibly remove eldest
     */
    private void afterNodeInsertion() {
        LRUNode<K, V> first;
        if ((first = head) != null && removeEldestEntry()) {
            removeFirst(first);
        }
    }

    private boolean removeEldestEntry() {
        return container.size() > capacity;
    }

    /**
     * 移除链表头部节点，即最近最少使用的节点
     *
     * @param node
     * @return
     */
    private K removeFirst(LRUNode<K, V> node) {
        if (node == tail) {
            tail = tail.pre;
            if (tail != null){
                tail.next = null;
            }
        } else if (node == head) {
            head = head.next;
            if (head != null){
                head.pre = null;
            }
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        container.remove(node.key);
        return node.key;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 3 );

      /*  cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2) );*/

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }

}
