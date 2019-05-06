package com.hong.leetcode;

import java.util.PriorityQueue;

/**
 * Created by wanghong
 * Date 2019-05-06 14:35
 * Description:
 * 703. 数据流中的第K大元素
 */
public class Solution703 {

    private int k;
    private PriorityQueue<Integer> queue;

    public Solution703(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);
        for (int n:nums){
            add(n);
        }
    }

    /**
     * 维护一个最小堆，堆的元素个数为常量 k，新加入一个元素和堆顶比较，
     * 如果比堆顶元素小，丢弃，否则删除堆顶元素，插入新元素。
     *
     * @param val
     */
    public int add(int val) {
        if (queue.size() < k) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.remove();
            queue.add(val);
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        int k = 3;
        Solution703 kthLargest = new Solution703(k, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}
