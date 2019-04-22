package com.hong.trie;

import java.util.Stack;
import java.util.TreeMap;

/**
 * @author wanghong
 * @date 2019/04/21 22:29
 * <p>
 * 字典树的基础表示
 * <p>
 * leetcode-208.实现Trie 前缀树
 **/
public class Trie {

    private class Node {
        // 表示当前节点是否为一个单词的最后一个字符所在的节点（即某个单词的末尾）
        public boolean isWord;
        // 当前节点到它所有下一个节点的映射,TreeMap也可以换成HashMap,可以比较下两者的性能差异
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
    }

    /**
     * 获取Trie中存储的单词数量
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加一个新的单词
     *
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 判断word是否在Trie字典树中
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 判断Trie字典树中是否有以prefix为前缀的单词
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

    /**
     * 在Trie中删除word
     *
     * @param word
     * @return
     */
    public boolean remove(String word) {
        // 将搜索沿路的节点放入栈中
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!stack.peek().next.containsKey(c)) {
                return false;
            }
            stack.push(stack.peek().next.get(c));
        }

        // 如果Trie中不包含该单词，直接返回false
        if (!stack.peek().isWord) {
            return false;
        }

        // 将该单词结尾isWord置空
        stack.peek().isWord = false;
        size--;

        /**
         * 如果单词最后一个字母的节点的next非空，
         * 说明trie中还存储了其他以该单词为前缀的单词，直接返回
         */
        if (stack.peek().next.size() > 0) {
            return true;
        }

        stack.pop();
        // 自底向上删除
        for (int i = word.length() - 1; i >= 0; i--) {
            stack.peek().next.remove(word.charAt(i));
            // 如果一个节点的isWord为true，或者是其他单词的前缀，则直接返回
            if (stack.peek().isWord || stack.peek().next.size() > 0){
                return true;
            }
        }

        return true;
    }

}
