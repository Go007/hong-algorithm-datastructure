package com.hong.bst;

/**
 * Created by wanghong
 * Date 2019-05-10 18:46
 * Description:
 */
public class TreeNode<E extends Comparable<E>> {

    public E val;
    public TreeNode left, right;

    public TreeNode(E val) {
        this.val = val;
    }

}
