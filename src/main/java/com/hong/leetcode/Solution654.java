package com.hong.leetcode;

import com.hong.bst.TreeNode;

/**
 * @author wanghong
 * @date 2019/05/14 22:28
 * TODO 654. 最大二叉树
 **/
public class Solution654 {

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int l, int r) {
        if (l >= r) {
            return new TreeNode(nums[l]);
        }

        int max = nums[l];
        int maxIndex = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        TreeNode node = new TreeNode(max);
        if (maxIndex > l) {
            node.left = build(nums, l, maxIndex - 1);
        }
        if (maxIndex < r) {
            node.right = build(nums, maxIndex + 1, r);
        }

        return node;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        TreeNode root = constructMaximumBinaryTree(nums);
        root.levelOrder();
    }
}
