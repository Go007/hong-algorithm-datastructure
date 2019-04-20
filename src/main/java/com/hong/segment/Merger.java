package com.hong.segment;

/**
 * @author wanghong
 * @date 2019/04/19 10:35
 * 合并工具
 **/
@FunctionalInterface
public interface Merger<E> {
    E merge(E a, E b);
}
