package com.hong.unionfind;

/**
 * @author wanghong
 * @date 2019/04/24 21:08
 * 并查集第二版-Quick Union
 **/
public class UnionFind2 implements UF{

    /**
     * 使用数组构建一颗指向父节点的树
     * parent[i] 表示第i个元素所指向的父节点
     */
    private int[] parent;

    /**
     * 构造函数中，默认parent[i] = i;
     *
     * @param n
     */
    public UnionFind2(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     *  O(h)复杂度, h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        parent[pRoot] = qRoot;
    }

    /**
     * 查找过程, 查找元素p所对应的集合编号
     *  O(h)复杂度, h为树的高度
     * @param p
     * @return
     */
    public int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }

        /**
         * 不断去查询自己的父亲节点, 直到到达根节点
         *  根节点的特点: parent[p] == p
         */
        while (p != parent[p]){
            p = parent[p];
        }

        return p;
    }
}
