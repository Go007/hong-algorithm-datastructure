package com.hong.avl;

import com.hong.util.FileOperation;

import java.util.ArrayList;

/**
 * @author wanghong
 * @date 2019/04/29 19:25
 * 红黑树的基本实现
 *
 * 红黑树和2-3树的等价性
 *   红黑树中的红色节点等价于该节点和其父节点在2-3树中是融合在一起的。
 *    红黑树               2-3树
 *    42黑色                                            42             42黑             42红
 *   /    \     ----》   37 42 66 临时4节点  ----》   /   \  -----》  /   \    -----》/    \
 *  37红  66红                                      37    66         37黑  66黑      37黑   66黑
 *
 *  2-3树中的3个2节点，对应红黑树中，表示这3个节点都是黑色的
 *  每一个黑色节点如果左侧没有红色节点的话，该节点就代表一个单独的2节点
 *  在2-3树种，临时4节点拆解为3个2节点后，中间节点要向上与他的父节点进行融合，
 *  对应红黑树种，就是该节点要颜色翻转为红色。flipColors
 *
 *  红黑树是一颗保持“黑平衡”的二叉树，严格意义上，不是平衡二叉树，
 *  最大高度： 2logN
 **/
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 判断节点的颜色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /**
     *     //   node                     x
     *     //  /   \     左旋转         /  \
     *     // T1   x   --------->   node   T3
     *     //     / \              /   \
     *     //    T2 T3            T1   T2
     * 左旋转
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }


    /**
     * 右旋转
     *            node                   x
     *     //    /   \     右旋转       /  \
     *     //   x    T2   ------->   y   node
     *     //  / \                       /  \
     *     // y  T1                     T1  T2
     * @param node
     * @return
     */
    private Node rightRotate(Node node){

        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node){

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向红黑树中添加新的元素(key, value)
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 最终根节点为黑色节点
    }

    /**
     *   向以node为根的红黑树中插入元素(key, value)，递归算法
     *   返回插入新节点后红黑树的根
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);// 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);


        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        node.value = newValue;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.right = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src\\main\\resources\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
