package com.hong.collection;

import com.hong.bst.BST;

/**
 * @author wanghong
 * @date 2019/04/15 21:02
 * 基于二叉搜索树实现的Set
 *  时间复杂度  平均    最差    准确的计算
 *  add      O(lgN)    O(N)    O(H)   H为树的高度
 *  contains O(lgN)    O(N)    O(H)
 *  remove   O(lgN)   O(N)    O(H)
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
