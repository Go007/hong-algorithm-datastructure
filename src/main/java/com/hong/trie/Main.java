package com.hong.trie;

/**
 * Created by wanghong
 * Date 2019-04-22 08:49
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apple");
        System.out.println(trie.contains("apple"));
        System.out.println(trie.contains("app"));
        System.out.println(trie.isPrefix("app"));
        trie.add("app");
        System.out.println(trie.contains("app"));
    }
}
