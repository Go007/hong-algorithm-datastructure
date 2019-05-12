package com.hong.leetcode;

import com.hong.sort.Bst;

/**
 * @author wanghong
 * @date 2019/05/12 18:01
 * 110. 平衡二叉树
 **/
public class Solution110 {

    public static boolean isBalanced(Bst.TreeNode root) {
        if (root == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 求以node为根节点的二叉树的高度
     *
     * @param node
     * @return
     */
    private static int height(Bst.TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 获取的节点node的平衡因子,即node的左右子树的高度差
     *
     * @param node
     * @return
     */
    private static int getBalanceFactor(Bst.TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,4,4};
        Bst bst = new Bst(nums);
        Bst.TreeNode root = bst.root;
        System.out.println(isBalanced(root));
    }
}
