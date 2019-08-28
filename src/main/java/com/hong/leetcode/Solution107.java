package com.hong.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wanghong
 * Date 2019-08-28 13:31
 * Description
 * 107. 二叉树的层次遍历 II:
 */
public class Solution107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
       // List<List<Integer>> res = new ArrayList<>();
        LinkedList<List<Integer>> res = new LinkedList<>();
        dfs(res,root,0);
        return res;
    }

    private void dfs(LinkedList<List<Integer>> res, TreeNode root, int level) {
       if (root == null){
           return;
       }

       // 当前层第一个节点，新建一个空列表
       if (res.size() == level){
           //res.add(0,new ArrayList<>());
           /**
            * 这里如果用ArrayList，会产生数据右移的操作
            * 用链表的话，直接在头部添加更方便
            */
           res.addFirst(new ArrayList<>());
       }
       res.get(res.size()-1-level).add(root.val);

       dfs(res,root.left,level+1);
       dfs(res,root.right,level+1);
    }

    /**
     * 非递归 BFS相比于DFS要简单些，因为BFS是一次性把当前层的元素都添加到ans中
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root){
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 当前层的节点集合
        List<Integer> subList = null;
        while (!queue.isEmpty()){
            // 当前层节点个数
            int levelSize = queue.size();
            subList = new ArrayList<>();
            for (int i=0;i<levelSize;i++){
                TreeNode node = queue.poll();
                if (node != null){
                    subList.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            if (subList.size() > 0){
                res.addFirst(subList);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] nums = {3,9,20,null,null,15,7};
        TreeNode node = new TreeNode();
        TreeNode root = node.build(nums);
        Solution107 solution107 = new Solution107();
        List<List<Integer>> lists = solution107.levelOrderBottom2(root);
        lists.stream().forEach(l -> {
            l.stream().forEach(i -> System.out.print(i + ","));
            System.out.println();
        });
    }

}
