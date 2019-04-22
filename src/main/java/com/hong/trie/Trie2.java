package com.hong.trie;

/**
 * Created by wanghong
 * Date 2019-04-22 17:09
 * Description: 基于数组实现的Trie字典树
 */
public class Trie2 {

    private class Node {

        public boolean isWord;
        public Node[] next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new Node[26];
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie2() {
        root = new Node();
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null){
                cur.next[c - 'a'] = new Node();
            }

            cur = cur.next[c - 'a'];
        }

        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

}
