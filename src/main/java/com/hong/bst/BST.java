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

    public TreeNode<E> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   /* public void add(E e) {
        if (root == null) {
            root = new TreeNode<E>(e);
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
    private TreeNode<E> add(TreeNode<E> node, E e) {
        if (node == null) {
            size++;
            return new TreeNode<E>(e);
        }

        if (e.compareTo(node.val) < 0) {
            node.left = add(node.left, e);
        }

        if (e.compareTo(node.val) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /*
    private void add(TreeNode<E> node, E e) {
        if (e.compareTo(node.val) == 0) {
            return;
        }

        if (e.compareTo(node.val) < 0 && node.left == null) {
            node.left = new TreeNode<E>(e);
            size++;
            return;
        }

        if (e.compareTo(node.val) > 0 && node.right == null) {
            node.right = new TreeNode<E>(e);
            size++;
            return;
        }

        if (e.compareTo(node.val) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }*/

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(TreeNode<E> node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.val) == 0) {
            return true;
        }

        if (e.compareTo(node.val) < 0) {
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

        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode<E> pop = stack.pop();
            sb.append(pop.val + "->");
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
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode<E> cur = queue.remove();
            sb.append(cur.val + "->");

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

        TreeNode<E> minNode = minimum(root);
        return minNode.val;
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

        TreeNode<E> maxNode = maximum(root);
        return maxNode.val;
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

    private void preOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    private void postOrder(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    private TreeNode<E> minimum(TreeNode<E> node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    private TreeNode<E> maximum(TreeNode<E> node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    private TreeNode<E> removeMin(TreeNode<E> node) {
        if (node.left == null) {
            TreeNode<E> right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private TreeNode<E> removeMax(TreeNode<E> node) {
        if (node.right == null) {
            TreeNode<E> left = node.left;
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
    private TreeNode<E> remove(TreeNode<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.val) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.val) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            /**
             * 如果待删除的节点的左子树为空，则直接将其右孩子代替它
             * 如果待删除的节点的右子树为空，则直接将其左孩子代替它
             */
            if (node.left == null) {
                TreeNode<E> right = node.right;
                node.right = null;
                size--;
                return right;
            }

            if (node.right == null) {
                TreeNode<E> left = node.left;
                node.left = null;
                size--;
                return left;
            }

            /**
             * 如果待删除节点的左右孩子均不为空，则找到比待删除节点
             * 大的最小节点，即待删除节点右子树的最小值节点，用它代替待删除节点
             */
            TreeNode<E> successor = minimum(node);
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
    private void generateBSTString(TreeNode<E> node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.val + "\n");
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
