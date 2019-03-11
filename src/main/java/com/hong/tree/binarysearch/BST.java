package com.hong.tree.binarysearch;

/**
 * @author Macrowang
 * @date 2019/03/11 9:41
 * 二分搜索树，Key需要可以比较，所以extends Comparable
 **/
public class BST<K extends Comparable<K>,V> {

    /**
     * 树中节点私有类，
     * 外界不需要了解具体实现
     */
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int count;

    /**
     * 默认构造一颗空节点
     */
    public BST() {
        root =null;
        count = 0;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }
}
