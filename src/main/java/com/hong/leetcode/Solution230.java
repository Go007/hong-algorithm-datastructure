package com.hong.leetcode;

import com.hong.bst.BST;
import com.hong.bst.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-05-10 18:19
 * Description:
 * 230. 二叉搜索树中第K小的元素
 */
public class Solution230 {

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>(k);
        inOrder(root, list, k);
        return list.get(k - 1);
    }

    private static void inOrder(TreeNode node, List<Integer> list, int k) {
        if (node == null || list.size() == k) {
            return;
        }

        inOrder(node.left, list,k);
        list.add((Integer) node.val);
        inOrder(node.right, list,k);
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 3, 6, 2, 4, 1};
        BST<Integer> bst = new BST();
        bst.build(nums);
        TreeNode<Integer> root = bst.root;
        System.out.println(kthSmallest(root, 3));
    }

}
