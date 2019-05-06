package com.hong.concurrent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.ToLongFunction;

/**
 * @param <T> 节点类型
 * @author wanghong
 * @date 2019/05/06 22:15
 * 一致性Hash算法的简单实现（暂未考虑hash冲突的问题）
 * https://www.cnblogs.com/moonandstar08/p/5405991.html
 **/
public class ConsistentHash<T> {

    /**
     * Hash计算函数，用于自定义hash算法
     */
    private static ToLongFunction<String> hashFunc;

    /**
     * 每个真实物理节点拥有的虚拟节点数量, 应对节点的增减，使数据分布更均匀
     */
    private int virtualNodeNums;

    /**
     * 使用TreeMap模拟一致性Hash环
     */
    private SortedMap<Long, T> circle = new TreeMap<>();

    /**
     * 构造，使用Java默认的Hash算法
     *
     * @param virtualNodeNums
     * @param nodes
     */
    public ConsistentHash(int virtualNodeNums, Collection<T> nodes) {
        this.virtualNodeNums = virtualNodeNums;
        this.hashFunc = key -> md5HashingAlg(key);
        //初始化节点
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 构造
     *
     * @param hashFunc        hash算法对象
     * @param virtualNodeNums 复制的节点个数，增加每个节点的复制节点有利于负载均衡
     * @param nodes           节点对象
     */
    public ConsistentHash(ToLongFunction<String> hashFunc, int virtualNodeNums, Collection<T> nodes) {
        this.virtualNodeNums = virtualNodeNums;
        this.hashFunc = hashFunc;
        //初始化节点
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 增加节点<br>
     * 每增加一个节点，就会在闭环上增加给定复制节点数<br>
     * 例如复制节点数是2，则每调用此方法一次，增加两个虚拟节点，这两个节点指向同一Node
     * 由于hash算法会调用node的toString方法，故按照toString去重
     *
     * @param node 节点对象
     */
    public void add(T node) {
        for (int i = 0; i < virtualNodeNums; i++) {
            circle.put(hash(node.toString() + i), node);
        }
    }

    /**
     * 移除节点的同时移除相应的虚拟节点
     *
     * @param node 节点对象
     */
    public void remove(T node) {
        for (int i = 0; i < virtualNodeNums; i++) {
            circle.remove(hash(node.toString() + i));
        }
    }

    /**
     * 获得一个最近的顺时针节点
     *
     * @param key 为给定键取Hash，取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * @return 节点对象
     */
    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        long hash = hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash); //返回此映射的部分视图，其键大于等于 hash
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        //正好命中
        return circle.get(hash);
    }

    private static long hash(String key) {
        return hashFunc.applyAsLong(key);
    }

    /**
     * 使用MD5算法
     *
     * @param key
     * @return
     */
    private static long md5HashingAlg(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 使用FNV1hash算法
     *
     * @param key
     * @return
     */
    private static long fnv1HashingAlg(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * 分布式测试
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("0001", "0002");
        ConsistentHash<String> k = new ConsistentHash<>(100, nodes);
        String str = "";
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < 10; i++) {
            String key = "user_" + i;
            // str += String.format("key:%s分配到的Server为：%s\n\n", key, k.get(key));
            //  System.out.println(str);
            String server = k.get(key);
            if ("0001".equals(server)) {
                num1++;
            } else if ("0002".equals(server)) {
                num2++;
            }
        }

        System.out.println(num1 + "  " + num2);
    }
}
