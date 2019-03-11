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
     * 在二分搜索树中查找key对应的value，没有则返回null
     * @param key
     * @return
     */
    public V search(K key){
        return search(root,key);
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

    /**
     * 在以node为根的二分搜索树中查找key所对应的value, 递归算法
     *  若key不存在，返回null
     * @param node
     * @param key
     * @return
     */
    public V search(Node node,K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0){
            return node.value;
        }else if (key.compareTo(node.key) < 0){
            return search(node.left,key);
        }else {
            return search(node.right,key);
        }
    }

    // 测试二分搜索树
    public static void main(String[] args) {

        int N = 1000000;

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
        }
    }
}