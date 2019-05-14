package com.hong.leetcode;

import com.hong.bst.BST;
import com.hong.bst.TreeNode;

/**
 * @author wanghong
 * @date 2019/05/14 19:09
 * 226. 翻转二叉树
 **/
public class Solution226 {

    public static TreeNode invertTree(TreeNode root) {

        if (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = right;
            root.right = left;
            invertTree(left);
            invertTree(right);
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] nums = {4, 2, 7, 1, 3, 6, 9};
        BST bst = new BST();
        bst.build(nums);
        TreeNode root = bst.root;
        root.levelOrder();
        root = invertTree(root);
        System.out.println();
        root.levelOrder();
    }
}
