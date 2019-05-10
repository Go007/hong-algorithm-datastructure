package com.hong.leetcode;

import com.hong.linked.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-05-10 19:18
 * Description:148. 排序链表
 */
public class Solution148 {

    public static ListNode sortList(ListNode head) {
        Bst bst = new Bst();
        while (head != null) {
            bst.add(head.val);
        }

        List<Integer> list = new ArrayList<>();
        bst.inOrder(list);
        ListNode newHead = null, tail = null;
        for (Integer i : list) {
            if (newHead == null) {
                newHead = tail = new ListNode(i);
            } else {
                tail = tail.next = new ListNode(i);
            }
        }
        return newHead;
    }

    private static class Bst {

        private class TreeNode {
            public int val;
            public TreeNode left, right;

            public TreeNode(int val) {
                this.val = val;
            }
        }

        public TreeNode root;


        public void add(int e) {
            root = add(root, e);
        }

        private TreeNode add(TreeNode node, int e) {
            if (node == null) {
                return new TreeNode(e);
            }

            if (e - node.val < 0) {
                node.left = add(node.left, e);
            }

            if (e - node.val > 0) {
                node.right = add(node.right, e);
            }

            return node;
        }

        public void inOrder(List<Integer> list) {
            inOrder(root, list);
        }

        private void inOrder(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }

            inOrder(node.left, list);
            list.add(node.val);
            inOrder(node.right, list);
        }
    }
}
