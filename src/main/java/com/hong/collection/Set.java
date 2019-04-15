package com.hong.collection;

/**
 * @author wanghong
 * @date 2019/04/15 21:01
 **/
public interface Set<E> {
    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}
