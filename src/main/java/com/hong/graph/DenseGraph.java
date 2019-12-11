package com.hong.graph;

import java.util.Vector;

/**
 * @author wanghong
 * @date 2019/12/11 22:25
 * <p>
 * 稠密图--邻接矩阵
 **/
public class DenseGraph {

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
     * g[i][j] == true 表示存在边从i到j
     */
    private boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        this.m = 0;
        // g初始化为n*n的布尔矩阵，每一个g[i][j]=false，表示没有任何边,
        // false为boolean型变量的默认值
        g = new boolean[n][n];
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

        if (hasEdge(v,w)){
            return;
        }

        g[v][w] = true;
        if (!directed){
            g[w][v] = true;
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

        return g[v][w];
    }

    /**
     *  返回图中一个顶点的所有邻边
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        if (v < 0 || v > n){
            throw new IllegalArgumentException("param is illegal.");
        }
        Vector<Integer> adjV = new Vector<>();
        for (int i = 0;i < n;i++){
            if (g[v][i]){
                adjV.add(i);
            }
        }
        return adjV;
    }
}
