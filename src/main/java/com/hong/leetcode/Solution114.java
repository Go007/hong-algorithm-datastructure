package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-08-28 15:46
 * Description:
 * 114. 二叉树展开为链表
 */
public class Solution114 {

    TreeNode tail = null;
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        if (tail != null){
            // 这里注意要将左子树置空
            tail.left = null;
            tail.right = root;
        }

        tail = root;
        // 前序：注意备份右子节点，规避下一节点篡改
        TreeNode copyRight = root.right;
        flatten(root.left);
        flatten(copyRight);
    }

    /**
     * 递归既简单又复杂
     */
    TreeNode pre = null;
    public void flatten2(TreeNode root) {
        if (root == null){
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root .right = pre;
        root.left = null;
        pre = root;
    }

}
