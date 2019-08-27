package com.hong.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wanghong
 * Date 2019-08-27 13:26
 * Description:
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){
    }

    TreeNode(int val){
        this.val = val;
    }

    public static TreeNode build(int[] nums){
        TreeNode root = new TreeNode(nums[0]);
        for (int i=1;i<nums.length;i++) {
            add(root,nums[i],i);
        }
        return root;
    }

    private static TreeNode add(TreeNode node, int e,int index){
        if (node == null) {
            return new TreeNode(e);
        }

        if (index % 2 == 0){
            node.right = add(node.right,e,index);
        }else {
            node.left = add(node.left,e,index);
        }

        return node;
    }

    public static void traverse(TreeNode root){
        // 使用Linked List作为队列使用
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            System.out.print(node.val + ",");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }



    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        TreeNode root = TreeNode.build(nums);
        traverse(root);
    }

}
