package com.hong.leetcode;

import com.hong.sort.Bst;

/**
 * Created by wanghong
 * Date 2019-07-31 10:58
 * Description:
 * 437. 路径总和 III
 * <p>
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Solution437 {

    public int pathSum(Bst.TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return traverse(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /**
     * 以每个节点为根节点，都算一遍路径和为sum的有几条，然后加起来
     * 效率不够高，存在大量重复计算
     *
     * @param node
     * @param sum
     * @return
     */
    private int traverse(Bst.TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

       /* int count = 0;
        sum -= node.val;
        if (sum == 0) {
            count++;
        }
        count += traverse(node.left, sum);
        count += traverse(node.right, sum);

        return count;*/
        sum -= node.val;
        return (sum == 0 ? 1 : 0) + traverse(node.left, sum) + traverse(node.right, sum);
    }

}
