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
     * Classic Non-Recursive algorithm for preorder traversal
     * Time Complexity: O(n), n is the node number in the tree
     * Space Complexity: O(h), h is the height of the tree
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
    public void levelOrder() {
        if (root == null) {
            return;
        }

        /**
         * 使用LinkedList add() 和remove() 作为
         * 队列的先入先出特性使用
         */
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            sb.append(cur.e + "->");

            if (cur.left != null) {
                queue.add(cur.left);
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb);
    }

    /**
     * 寻找二分搜索树最小节点值
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 寻找二分搜索树最大节点值
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        Node maxNode = maximum(root);
        return maxNode.e;
    }

    /**
     * 删除二分搜索树中最小值节点，并返回最小值
     *
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除二分搜索树中最大值节点，并返回最大值
     *
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除节点值为E的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
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

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除以node为根的二叉搜索树中节点数据域为e的节点，返回删除后的根节点
     *
     * @param node
     * @param e
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            /**
             * 如果待删除的节点的左子树为空，则直接将其右孩子代替它
             * 如果待删除的节点的右子树为空，则直接将其左孩子代替它
             */
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }

            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

            /**
             * 如果待删除节点的左右孩子均不为空，则找到比待删除节点
             * 大的最小节点，即待删除节点右子树的最小值节点，用它代替待删除节点
             */
            Node successor = minimum(node);
            successor.left = node.left;
            successor.right = removeMin(node);
            node.left = node.right = null;
            return successor;
        }
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
