package com.hong.graph;

/**
 * @author wanghong
 * @date 2019/12/14 16:46
 * 图的接口
 **/
public interface Graph {
    int V();
    int E();
    void addEdge(int v,int w);
    boolean hasEdge(int v,int w);
    void show();
    Iterable<Integer> adj(int v);
}
