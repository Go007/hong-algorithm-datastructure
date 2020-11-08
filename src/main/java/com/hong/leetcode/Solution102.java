package com.hong.leetcode;

import java.util.*;

/**
 * Created by wanghong
 * Date 2019-08-27 11:16
 * Description:
 * 102. 二叉树的层次遍历
 */
public class Solution102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> currentLevelList = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            currentLevelList = new ArrayList<>(size);
            // 当前队列的大小就是上一层节点个数，依次出队
            while (size > 0) {
                TreeNode currentNode = queue.poll();
                if (currentNode == null) {
                    continue;
                }
                currentLevelList.add(currentNode.val);
                // 左子树和右子树入队
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }

                size--;
            }
            res.add(currentLevelList);
        }

        return res;
    }

    // 递归
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        levelOrderHelper(res, root, 0);
        return res;
    }

    private void levelOrderHelper(List<List<Integer>> res, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        //当前层数还没有元素，先 new 一个空的列表
        if (res.size() <= depth) {
            res.add(new ArrayList<>());
        }

        // depth层，把当前节点加入
        res.get(depth).add(node.val);
        // 递归的遍历下一层
        levelOrderHelper(res, node.left, depth + 1);
        levelOrderHelper(res, node.right, depth + 1);
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> list = null;
        while (!queue.isEmpty()) {
            int size = queue.size();// 当前层的节点个数
            list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 当前层所有节点已遍历完，加入结果集中
            res.add(list);
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6};
        TreeNode treeNode = new TreeNode();
        TreeNode root = treeNode.build(nums);
        Solution102 s = new Solution102();
        List<List<Integer>> lists = s.levelOrder3(root);
        for (List<Integer> l : lists) {
            System.out.println(l);
        }
    }

}
