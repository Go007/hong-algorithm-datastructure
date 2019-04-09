package com.hong.bst;

/**
 * @author Macrowang
 * @date 2019/04/09 14:13
 * <p>
 * 二叉搜索树
 **/
public class BST<E extends Comparable<E>> {

    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    /**
     * 向以node为根的二分搜索树中添加数据域为e的新节点,
     * 如果树中已存在数据域为e的节点，则直接返回
     *
     * @param node
     * @param e
     */
    private void add(Node node, E e) {
        if (e.compareTo(node.e) == 0) {
            return;
        }

        if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }
}
