package com.hong.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   /* public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }
    */

    /**
     * 通过给定的数组构建二分搜索树
     *
     * @param nums
     */
    public void build(E[] nums) {
        for (E e : nums) {
            add(e);
        }
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中添加数据域为e的新节点,
     * 如果树中已存在数据域为e的节点，则直接返回
     *
     * @param node
     * @param e
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /*
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
    }*/

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    // 二分搜索树前序遍历
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            sb.append(pop.e + "->");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb);
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    // 层序遍历
    public void levelOrder(){
        if (root == null){
            return;
        }

        /**
         * 使用LinkedList add() 和remove() 作为
         * 队列的先入先出特性使用
         */
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            sb.append(cur.e + "->");

            if (cur.left != null){
                queue.add(cur.left);
            }

            if (cur.right != null){
                queue.add(cur.right);
            }
        }

        sb.delete(sb.length()-2,sb.length());
        System.out.println(sb);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {28, 16, 30, 13, 22, 29, 42};
        BST<Integer> bst = new BST();
        bst.build(nums);
        bst.preOrderNR();
        bst.levelOrder();
    }
}
