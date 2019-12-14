package com.hong.graph;

import java.util.Vector;

/**
 * @author wanghong
 * @date 2019/12/11 21:50
 * 图的表示 根据图中每个节点平均连接情况，将图分为 稀疏图 和 稠密图
 * 如果一个图中的节点连接其他节点的个数远小于节点的总个数，就可以作为稀疏图。
 * 稀疏图 -- 邻接表
 **/
public class SparseGraph implements Graph {

    /**
     * 节点个数
     */
    private int n;

    /**
     * 边数
     */
    private int m;

    /**
     * 是否为有向图
     */
    private boolean directed;

    /**
     * 图的具体数据
     */
    private Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        // 初始化没有任何边
        this.m = 0;
        // g初始化为n个空的vector，表示每一个g[i]都为空，即没有任何边
        this.g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<>();
        }
    }

    /**
     * 返回节点个数
     *
     * @return
     */
    public int V() {
        return n;
    }

    /**
     * 返回边的个数
     *
     * @return
     */
    public int E() {
        return m;
    }

    /**
     * 向图中添加一个边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        if (v < 0 || v > n || w < 0 || w > n) {
            throw new IllegalArgumentException("param is illegal.");
        }

        if (hasEdge(v, w)) {
            return;
        }
        g[v].add(w);
        // 如果v，w不相等，且是无向图，则需要将边在连接的两个节点中都要保存数据
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    /**
     * 验证是否有从v到w的边
     *
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v > n || w < 0 || w > n) {
            throw new IllegalArgumentException("param is illegal.");
        }

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) {
                return true;
            }
        }

        return false;
    }

    /**
     * 显示图的信息
     */
    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++)
                System.out.print(g[i].elementAt(j) + "\t");
            System.out.println();
        }
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外开销
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException("param is illegal.");
        }

        return g[v];
    }

    public static void main(String[] args) {
        SparseGraph graph = new SparseGraph(7, false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 6);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        System.out.println(graph.hasEdge(2, 4));
    }
}
