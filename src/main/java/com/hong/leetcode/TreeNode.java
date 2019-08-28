package com.hong.leetcode;

import java.util.List;

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

    public TreeNode build(int[] nums){
        return createTree(nums,0);
    }

    private TreeNode createTree(int[] nums, int index) {
        TreeNode root = null;
        if (index < nums.length){
            root = new TreeNode(nums[index]);
            root.left = createTree(nums,leftChild(index));
            root.right = createTree(nums,rightChild(index));
        }
        return root;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }

        return (index - 1) / 2;
    }

    public static void main(String[] args) {
        int[] nums = {3,9,20,-1,-1,15,7};
        TreeNode node = new TreeNode();
        TreeNode root = node.build(nums);
        Solution102 solution102 = new Solution102();
        List<List<Integer>> lists = solution102.levelOrder(root);
        lists.stream().forEach(l -> {
            l.stream().forEach(i -> System.out.print(i + ","));
            System.out.println();
        });
        TreeOperation.show(root);
    }

}
