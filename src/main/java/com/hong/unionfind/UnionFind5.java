package com.hong.unionfind;

/**
 * @author wanghong
 * @date 2019/04/24 22:43
 *  并查集优化第五版 - 路径压缩
 **/
public class UnionFind5 implements UF{
    private int[] parent;
    /**
     *  使用数组记录每棵树的高度
     *  rank[i]表示以i为根的集合所表示的树的层数
     *  在后续的代码中, 我们并不会维护rank的语意,
     *  也就是rank的值在路径压缩的过程中,有可能不在是树的层数值
     *  这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
     */
    private int[] rank;

    public UnionFind5(int size) {
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
            // 路径压缩就这么核心的一句代码
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }
}
