package com.hong.leetcode;

import com.hong.sort.Bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-07-30 14:29
 * Description:
 *
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 */
public class Solution112 {

    /**
     * 递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(Bst.TreeNode root, int sum) {
        if (root == null){
            return false;
        }

        sum -= root.val;
        if (root.left == null && root.right == null){
            return sum == 0;
        }

        return hasPathSum(root.left,sum)
                || hasPathSum(root.right,sum);
    }

    /**
     * 迭代
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(Bst.TreeNode root, int sum){
        if (root == null){
            return false;
        }

        LinkedList<Bst.TreeNode> nodeStack = new LinkedList<>();
        LinkedList<Integer> sumStack = new LinkedList<>();

        nodeStack.push(root);
        sumStack.push(sum-root.val);

        Bst.TreeNode node = null;
        int curSum;
        while (!nodeStack.isEmpty()){
            node = nodeStack.poll();
            curSum = sumStack.poll();

            if (node.left == null && node.right == null && curSum ==0){
                return true;
            }

            if (node.right != null){
                nodeStack.push(node.right);
                sumStack.push(curSum - node.right.val);
            }

            if (node.left != null){
                nodeStack.push(node.left);
                sumStack.push(curSum - node.left.val);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[] nums = {5,4,8,11,13,4,7,2,1};
        Bst bst = new Bst(nums);
        Bst.TreeNode root = bst.root;
        List<Integer> list = new ArrayList<>();
        bst.inOrder(list);
        for (int i:list){
            System.out.print(i + " ");
        }
    }

}
