package com.hong.trie;

import java.util.TreeMap;

/**
 * Created by wanghong
 * Date 2019-04-22 10:59
 * Description: Trie字典树和字符串的映射
 */
public class MapSum {

    private class Node {
        public int value;

        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node){
        int res = node.value;
        for (Node n:node.next.values()){
            res += sum(n);
        }

        return res;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("panda",1);
        mapSum.insert("pan",1);
        int sum = mapSum.sum("pa");
        System.out.println(sum);
    }

}
