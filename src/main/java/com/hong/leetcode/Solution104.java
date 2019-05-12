package com.hong.leetcode;

import com.hong.sort.Bst;

/**
 * @author wanghong
 * @date 2019/05/12 17:23
 * 104. 二叉树的最大深度
 **/
public class Solution104 {

    public static int maxDepth(Bst.TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 9, 20, 15, 7};
        Bst bst = new Bst(nums);
        Bst.TreeNode root = bst.root;
        System.out.println(maxDepth(root));
    }
}
