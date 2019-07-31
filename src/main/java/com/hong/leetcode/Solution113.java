package com.hong.leetcode;

import com.hong.sort.Bst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghong
 * Date 2019-07-31 09:17
 * Description:
 *
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 */
public class Solution113 {

    /**
     * DFS+回溯
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(Bst.TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root,sum,result,new ArrayList<>());
        return result;
    }

    private void traverse(Bst.TreeNode node, int sum, List<List<Integer>> result, ArrayList<Integer> path) {
        if (node == null){
            return;
        }

        // 将沿途遍历到的节点加入到path中
        sum -= node.val;
        path.add(node.val);
        if (node.left == null && node.right == null){
            // 当前路径满足条件，复制(这里一定要是复制，进行后面的回朔)path到result中
            if (sum == 0){
                result.add(new ArrayList<>(path));
            }
        }else {
            traverse(node.left,sum,result,path);
            traverse(node.right,sum,result,path);
        }

        //回溯
        path.remove(path.size() - 1);
    }


}
