package com.hong.lfu;

/**
 * Created by wanghong
 * Date 2019-07-17 14:12
 * Description:
 * Redis4.0 中引入了LFU算法 (Least Frequently Used) 最近不经常使用
 * 核心思想：根据key的历史访问频率淘汰key，基于这样一种假设：如果数据过去被访问多次，那么将来被访问的频率也更高。
 * 把数据加入到链表中，按频次排序，一个数据被访问过，把它的频次+1，发生淘汰的时候，把频次低的淘汰掉。
 * 将key平时的访问活跃度纳入淘汰策略的考量中
 *
 * https://www.cnblogs.com/qingdaofu/p/7459248.html
 * https://www.jianshu.com/p/1f8e36285539
 */
public class LFUCache {
}
