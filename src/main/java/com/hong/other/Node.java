package com.hong.other;

/**
 * Created by John on 2019/1/24.
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
