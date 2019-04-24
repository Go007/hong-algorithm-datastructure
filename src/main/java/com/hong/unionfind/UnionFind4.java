package com.hong.unionfind;

/**
 * @author wanghong
 * @date 2019/04/24 21:45
 * <p>
 * 并查集第四版-基于rank的优化
 **/
public class UnionFind4 implements UF {

    private int[] parent;
    /**
     *  使用数组记录每棵树的高度
     *  rank[i]表示以i为根的集合所表示的树的层数
     */
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
         *  根据两个元素所在树的rank不同判断合并方向
         *  将rank低的集合合并到rank高的集合上
         */
        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        }else {
            // 此时, 维护rank的值
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
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
