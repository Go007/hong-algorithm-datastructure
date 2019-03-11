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

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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

    public void insert(K key,V value){
        root = insert(root,key,value);
    }

    public boolean contain(K key){
        return contain(root,key);
    }

    /**
     * 向以node为根节点的二分搜索树中插入新的节点，返回插入后新的根节点
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node node,K key,V value){
        if (node == null){
            return new Node(key,value);
        }

        if (key.compareTo(node.key) == 0){
            node.value = value;
        }else if (key.compareTo(node.key) < 0){
            node.left = insert(node.left,key,value);
        }else {
            node.right = insert(node.right,key,value);
        }

        return node;
    }

    /**
     *  查看以node为根节点的二分搜索树中是否存在键值为key的节点
     * @param node
     * @param key
     * @return
     */
    public boolean contain(Node node,K key){
        if (node == null){
            return false;
        }

        if (key.compareTo(node.key) == 0){
            return true;
        }else if (key.compareTo(node.key) < 0){
            return contain(node.left,key);
        }else {
            return contain(node.right,key);
        }
    }

}