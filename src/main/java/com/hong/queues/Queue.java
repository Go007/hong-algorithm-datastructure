package com.hong.queues;

/**
 * @author wanghong
 * @date 2019/04/06 23:01
 * 队列定义接口
 * 也是一种线性结构
 * 相较于数组，对应的操作是数组的子集
 * 只能从队尾添加元素，从队首取出元素
 **/
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
