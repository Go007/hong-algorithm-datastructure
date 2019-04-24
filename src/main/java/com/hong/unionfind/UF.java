package com.hong.unionfind;

/**
 * @author wanghong
 * @date 2019/04/24 19:51
 * 并查集接口
 **/
public interface UF {

    int getSize();
    boolean isConnected(int p,int q);
    void unionElements(int p,int q);
}
