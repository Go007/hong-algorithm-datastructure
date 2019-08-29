package com.hong.leetcode;

/**
 * Created by wanghong
 * Date 2019-08-29 14:47
 * Description:
 * 236. 二叉树的最近公共祖先
 */
public class Solution236 {

    /**
     * 从根节点开始遍历，如果node1和node2中的任一个和root匹配，那么root就是最低公共祖先。
     * 如果都不匹配，则分别递归左、右子树，如果有一个节点出现在左子树，并且另一个节点出现在右子树，
     * 则root就是最低公共祖先. 如果两个节点都出现在左子树，则说明最低公共祖先在左子树中，否则在右子树。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //发现目标节点则通过返回值标记该子树发现了某个目标结点
        if(root == null || root == p || root == q) return root;
        //查看左子树中是否有目标结点，没有为null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //查看右子树是否有目标节点，没有为null
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //都不为空，说明左右子树都有目标结点，则公共祖先就是本身
        if(left!=null&&right!=null) return root;
        //如果发现了目标节点，则继续向上标记为该目标节点
        return left == null ? right : left;
    }

    /**
     * TODO 最近公共祖先，Targin算法
     */

}
