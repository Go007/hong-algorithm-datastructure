package com.hong.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wanghong
 * Date 2019-05-10 18:46
 * Description:
 */
public class TreeNode<E extends Comparable<E>> {

    public E val;
    public TreeNode left, right;

    public TreeNode(E val) {
        this.val = val;
    }

    public void print(TreeNode node, int depth) {
        String s = "/";
        char c = 92;
        if (node != null && depth > 0) {
            for (int i = 1; i <= (depth - 1) * 2; i++) {
                System.out.print(" ");
            }
            System.out.println(node.val);
            for (int i = 1; i <= (depth - 1) * 2 - 1; i++) {
                System.out.print(" ");
            }
            System.out.println(s + c);
        }
    }

    public void levelOrder() {
        // 我们使用LinkedList来作为我们的队列
        Queue<TreeNode> q = new LinkedList<>();
        q.add(this);
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            System.out.print(node.val + "->");
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
    }

    public int height() {
        return height(this);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

}
