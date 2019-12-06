package com.hong.tree;

import com.hong.queues.LoopQueue;
import com.hong.queues.LoopQueue3;
import javafx.util.Pair;

import java.util.*;

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

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
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

    public void inOrderByStack() {
        inOrderByStack(root);
    }

    /**
     * 二叉搜索树的广度优先遍历(层序遍历)
     */
    public void levelOrder() {
        // 使用Linked List作为队列使用
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.key + ",");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 删除二分搜索树中最小值节点
     */
    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    /**
     * 删除二分搜索树中最大值节点
     */
    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }

    /**
     * 从二分搜索树中删除指定K的节点
     *
     * @param k
     */
    public void remove(K k) {
        root = remove(root, k);
    }

    // 返回以node为根的二分搜索树的最小键值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
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
     * 后序遍历结果是：13  22  16  29  42  30  28
     * 前序遍历   我你他
     * 中序遍历  你我他
     * 后序遍历  你他我
     * <p>
     * 广度优先遍历：28，16，30，13，22，29，42
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

    private void inOrderByStack(Node node) {
        if (node == null) return;

        LinkedList<Node> stack = new LinkedList<>();
        Node cur = node;
        while (cur != null || !stack.isEmpty()) {
            // 一直循环到二叉排序树最左端的叶子节点
            while (cur != null) {
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
            postOrder(node.right);
            System.out.print(node.key + ",");
        }
    }

    /**
     * 删除以node为根的二分搜索树中的最小节点
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除以node为根的二分搜索树中的最大节点
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            count--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除以node为根的二分搜索树中指定key的节点，
     * 返回删除后新的root
     * Hubbard Deletion
     * @param node
     * @param k
     * @return
     */
    private Node remove(Node node, K k) {
        if (node == null) return null;

        if (k.compareTo(node.key) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.key) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {
            /**
             * 找到待删除节点后，需要判断三种情况
             * 1. 待删除节点左子树为空
             * 2. 待删除节点右子树为空
             * 3. 待删除节点左右子树都不为空
             */
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                count--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }

            /**
             *   找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
             *   用这个节点顶替待删除节点的位置
             */
            Node successor = new Node(minimum(node.right));
            // TODO 这里为什么要先count++，下面再count-- ?
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            count--;

            return successor;
        }
    }

    /**
     * Leetcode 102. Binary Tree Level Order Traversal
     * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * 二叉树的层序遍历
     * 二叉树的层序遍历是一个典型的可以借助队列解决的问题。
     * 该代码主要用于使用Leetcode上的问题测试LoopQueue。
     **/
    public List<List<V>> levelOrder(Node root) {
        /**
         * 集合中的泛型List<Integer>表示二叉树中每个层级的节点集
         */
        ArrayList<List<V>> res = new ArrayList<>();
        if (root == null)
            return res;

        /**
         * key:层级根是0，依次递增
         * value:二叉树中每个层级的节点的属性值集
         */
        Map<Integer, List<V>> map = new LinkedHashMap<>();

        /**
         *  使用自定义的循环队列，泛型Pair<TreeNode, Integer>表示 TreeNode 和所属层级的配对
         */
        LoopQueue<Pair<Node, Integer>> queue = new LoopQueue<>();
        // 层级从0开始，root就是0级
        queue.enqueue(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<Node, Integer> front = queue.dequeue();
            Node node = front.getKey();
            int level = front.getValue();

            List<V> levelNodeList = map.get(level);
            if (levelNodeList == null) {
                levelNodeList = new ArrayList<>();
                map.put(level, levelNodeList);
            }
            levelNodeList.add(node.value);

            if (node.left != null)
                queue.enqueue(new Pair<>(node.left, level + 1));
            if (node.right != null)
                queue.enqueue(new Pair<>(node.right, level + 1));
        }

        for (Integer level : map.keySet()) {
            res.add(map.get(level));
        }

        return res;
    }

    public List<List<V>> levelOrder2(Node root) {
        ArrayList<List<V>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LoopQueue3<Pair<Node, Integer>> queue = new LoopQueue3<>();
        queue.enqueue(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<Node, Integer> front = queue.dequeue();
            Node node = front.getKey();
            int level = front.getValue();

            /**
             * 因为时层序遍历，res的size跟随level的变化，且同一层级，两者的数值是相等的
             */
            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            assert level < res.size();
            res.get(level).add(node.value);
            if (node.left != null)
                queue.enqueue(new Pair<>(node.left, level + 1));
            if (node.right != null)
                queue.enqueue(new Pair<>(node.right, level + 1));
        }

        return res;
    }

    /**
     * 寻找key的floor值，递归算法 <= key
     * 如果不存在key的floor值，即key比BST树中的最小值还要小，则返回null
     * @param key
     * @return
     */
    public K floor(K key){
        if (count == 0 || key.compareTo(minimum(root).key) < 0){
            return null;
        }

        Node floorNode = floor(root,key);
        return floorNode.key;
    }

    /**
     * 在以node为根的二叉搜索树中, 寻找key的floor值所处的节点, 递归算法
     * @param node
     * @param key
     * @return
     */
    private Node floor(Node node, K key) {
        if (node == null){
            return null;
        }

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的floor节点
        if( node.key.compareTo(key) == 0 )
            return node;

        // 如果node的key值比要寻找的key值大
        // 则要寻找的key的floor节点一定在node的左子树中
        if( node.key.compareTo(key) > 0 )
            return floor( node.left , key );

        // 如果node->key < key
        // 则node有可能是key的floor节点, 也有可能不是(存在比node->key大但是小于key的其余节点)
        // 需要尝试向node的右子树寻找一下
        Node tempNode = floor( node.right , key );
        if( tempNode != null )
            return tempNode;

        return node;
    }

    /**
     * 寻找key的ceil值，递归算法 >= key
     * 如果不存在key的ceil值，即key比BST树中的最大值还要大，则返回null
     * @param key
     * @return
     */
    public K ceil(K key){
        if (count == 0|| key.compareTo(maximum(root).key) > 0){
            return null;
        }

        Node ceilNode = ceil(root,key);
        return ceilNode.key;
    }

    /**
     * 在以node为根的二叉搜索树中, 寻找key的ceil值所处的节点, 递归算法
     * @param node
     * @param key
     * @return
     */
    private Node ceil(Node node, K key) {
        if( node == null )
            return null;

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的ceil节点
        if( node.key.compareTo(key) == 0 )
            return node;

        // 如果node的key值比要寻找的key值小
        // 则要寻找的key的ceil节点一定在node的右子树中
        if( node.key.compareTo(key) < 0 )
            return ceil( node.right , key );

        // 如果node->key > key
        // 则node有可能是key的ceil节点, 也有可能不是(存在比node->key小但是大于key的其余节点)
        // 需要尝试向node的左子树寻找一下
        Node tempNode = ceil( node.left , key );
        if( tempNode != null )
            return tempNode;

        return node;
    }

    // 测试二分搜索树
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        int[] arr = new int[]{28, 16, 13, 22, 30, 29, 42};
        for (int k : arr) {
            bst.insert(k, k + "");
        }

        bst.levelOrder();
        // bst.remove(16);
        System.out.println();
        //  bst.levelOrder();
        List<List<String>> levelList = bst.levelOrder(bst.getRoot());
        levelList.forEach(list -> System.out.println(list));
        System.out.println();
        levelList = bst.levelOrder2(bst.getRoot());
        levelList.forEach(list -> System.out.println(list));

        System.out.println("----------------");
        bst.postOrder();

        System.out.println();
        System.out.println(bst.count);
        bst.remove(16);
        System.out.println(bst.count);

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

    public Node getRoot() {
        return root;
    }
}