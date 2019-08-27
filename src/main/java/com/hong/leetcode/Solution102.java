package com.hong.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-08-27 11:16
 * Description:
 * 102. 二叉树的层次遍历
 */
public class Solution102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> currentLevelList = null;
        while (!queue.isEmpty()){
            int size = queue.size();
            currentLevelList = new ArrayList<>(size);
            // 当前队列的大小就是上一层节点个数，依次出队
            while (size > 0){
                TreeNode currentNode = queue.poll();
                if (currentNode == null){
                    continue;
                }
                currentLevelList.add(currentNode.val);
                // 左子树和右子树入队
                if (currentNode.left != null){
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null){
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
        if (root == null){
            return res;
        }

        levelOrderHelper(res,root,0);
        return res;
    }

    private void levelOrderHelper(List<List<Integer>> res, TreeNode node, int depth) {
        if (node == null){
            return;
        }

        if (res.size() <= depth){
            // 当前层的第一个节点
            res.add(new ArrayList<>());
        }

        // depth层，把当前节点加入
        res.get(depth).add(node.val);
        // 递归的遍历下一层
        levelOrderHelper(res,node.left,depth+1);
        levelOrderHelper(res,node.right,depth+1);
    }

}
