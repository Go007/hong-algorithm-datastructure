package com.hong.leetcode;

import com.hong.sort.Bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wanghong
 * @date 2019/06/07 18:46
 * 94. 二叉树的中序遍历
 **/
public class Solution94 {

    /**
     * 1.递归方式
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal1(Bst.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private static void inOrder(Bst.TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    /**
     * 使用栈迭代算法
     * 1、从根节点开始一直往左走，将经过的所有节点压栈；
     * 2、栈不空则出栈，将出栈节点添加到list并且转向该节点的右子树；
     * 3、栈为空则重复执行上述两个步骤。
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(Bst.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Bst.TreeNode> stack = new Stack<>();
        Bst.TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    public static List<Integer> inorderTraversal3(Bst.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Bst.TreeNode> stack = new Stack<>();
        Bst.TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 5, 7, 2, 8, 9, 3, 1};
        Bst bst = new Bst(arr);
        List<Integer> list = inorderTraversal2(bst.root);
        list.forEach(val -> System.out.print(val + ","));
    }
}
