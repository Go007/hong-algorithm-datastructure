package com.hong.collection;

import com.hong.linked.LinkedList;

/**
 * @author wanghong
 * @date 2019/04/15 21:19
 * 基于链表的集合实现
 * 时间复杂度
 * add        O(N)
 * contains   O(N)
 * remove     O(N)
 **/
public class LinkedListSet<E extends Comparable<E>> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)){
            list.addFirst(e);
        }
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
