package com.hong.unionfind;

/**
 * @author wanghong
 * @date 2019/04/24 21:45
 * <p>
 * 并查集第三版-基于size的优化
 **/
public class UnionFind3 implements UF {

    private int[] parent;
    /**
     * 使用数组记录每棵树的高度
     * sz[i] 表示以i为根的集合中元素个数
     */
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot){
            return;
        }

        /**
         * 根据两个元素所在树的元素个数不同判断合并方向
         * 将元素个数少的集合合并到元素个数多的集合上
         */
        if (sz[pRoot] > sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    public int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }

        while (p != parent[p]){
            p = parent[p];
        }

        return p;
    }
}
