package com.hong.leetcode;

import java.util.Stack;

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
        flatten2(root.right); // 先转换右子树
        flatten2(root.left);
        root.right = pre; // 右子树指向链表的头
        root.left = null;  // 左子树置空
        pre = root; // 当前节点变更为链表头
    }

    /**
     * 用一个stack记录节点，右子树先入栈，左子树后入栈，但是这样不满足原地展开
     * @param root
     */
    public void flatten3(TreeNode root){
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
            if (!stack.isEmpty()) current.right = stack.peek();
            current.left = null;
        }
    }

}
