package com.hong.sort;

import java.util.List;

/**
 * @author wanghong
 * @date 2019/05/11 9:28
 **/
public class Bst {
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root;

    public Bst(int[] nums) {
        for (int e : nums) {
            add(e);
        }
    }

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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(TreeNode node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.val + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
