package com.hong.stacks;

/**
 * @author wanghong
 * @date 2019/04/06 19:02
 *  栈数据结构接口定义
 *  栈和数组一样，也是一种线性结构
 *  相较于数组，栈对应的操作是数组的子集
 *  只能从同一端(栈顶)添加和取出元素
 *  栈的应用：
 *  程序调用的系统栈
 **/
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
