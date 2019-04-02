package com.hong.tree.binarysearch;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Macrowang
 * @date 2019/03/11 9:41
 * 二分搜索树，Key需要可以比较，所以extends Comparable
 **/
public class BST<K extends Comparable<K>, V> {

    /**
     * 树中节点私有类，
     * 外界不需要了解具体实现
     */
    private class Node {
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
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    public boolean contain(K key) {
        return contain(root, key);
    }

    /**
     * 在二分搜索树中查找key对应的value，没有则返回null
     *
     * @param key
     * @return
     */
    public V search(K key) {
        return search(root, key);
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    public void preOrderByStack() {
        preOrderByStack(root);
    }

    public  void inOrderByStack(){
        inOrderByStack(root);
    }

    //================================辅助函数==================================//

    /**
     * 向以node为根节点的二分搜索树中插入新的节点，返回插入后新的根节点
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node node, K key, V value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }

    /**
     * 查看以node为根节点的二分搜索树中是否存在键值为key的节点
     *
     * @param node
     * @param key
     * @return
     */
    public boolean contain(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }
    }

    /**
     * 在以node为根的二分搜索树中查找key所对应的value, 递归算法
     * 若key不存在，返回null
     *
     * @param node
     * @param key
     * @return
     */
    public V search(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    /**
     * 二分搜索树的深度优先遍历
     * 28
     * /      \
     * 16       30
     * /   \     /  \
     * 13    22  29   42
     * 对于上面的这个二分搜索树，
     * 前序遍历结果是：28  16  13  22  30  29  42，
     * 中序遍历结果是：13  16  22  28  29  30  42，
     * 后序遍历结果是：13  22  16  29  42  30  2
     * 前序遍历   我你他
     * 中序遍历  你我他
     * 后序遍历  你他我
     */
    // 对以node为根的二叉搜索树进行前序遍历, 递归算法
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + ",");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 对以node为根的二叉搜索树进行前序遍历, 使用栈，非递归算法
     * LinkedList.push()/pop()也可以实现类似栈的效果
     */
    private void preOrderByStack(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.key + ",");
            // 有序栈结构先进后出的特性，需要将右孩子先于左孩子压入栈底
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    // 对以node为根的二叉搜索树进行中序遍历, 递归算法
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + ",");
            inOrder(node.right);
        }
    }

    private void inOrderByStack(Node node){
        if (node == null) return;

        LinkedList<Node> stack = new LinkedList<>();
        Node cur = node;
        while (cur != null || !stack.isEmpty()){
            // 一直循环到二叉排序树最左端的叶子节点
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.print(cur.key + ",");
            cur = cur.right;
        }
    }

    // 对以node为根的二叉搜索树进行后序遍历, 递归算法
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            preOrder(node.right);
            System.out.print(node.key + ",");
        }
    }

    // 测试二分搜索树
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        int[] arr = new int[]{28, 16, 13, 22, 30, 29, 42};
        for (int k : arr) {
            bst.insert(k, k + "");
        }
      /*  bst.preOrder();
        System.out.println();
        bst.preOrderByStack();
        System.out.println();*/
        bst.inOrder();
        System.out.println();
        bst.inOrderByStack();
       /* System.out.println();
        bst.postOrder();*/

/*        int N = 10;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 打乱数组顺序
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i+1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）

        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer,String> bst = new BST<>();
        for(int i = 0 ; i < N ; i ++)
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));

        // 对[0...2*N)的所有整型测试在二分搜索树中查找
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        for(int i = 0 ; i < 2*N ; i ++){
            String res = bst.search(new Integer(i));
            if( i < N )
                assert res.equals(Integer.toString(i));
            else
                assert res == null;
        }*/
    }
}