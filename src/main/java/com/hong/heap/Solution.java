package com.hong.heap;

import java.util.*;

/**
 * @author wanghong
 * @date 2019/04/17 9:57
 **/
public class Solution {

    private class Freq implements Comparable<Freq> {

        private int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            if (this.freq < o.freq) {
                return 1;
            } else if (this.freq > o.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 347. 前K个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        MaxHeap<Freq> heap = new MaxHeap<>();
        for (Integer key : map.keySet()) {
            if (heap.size() < k) {
                heap.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > heap.findMax().freq) {
                heap.extractMax();
                heap.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!heap.isEmpty())
            res.add(heap.extractMax().e);
        return res;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }

        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, (i, j) -> {
            if (i.compareTo(j) <= 0) {
                return 1;
            } else {
                return -1;
            }
        });

        List<Integer> res = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            for (Integer key : map.keySet()) {
                if (map.get(key) == list.get(i)) {
                    res.add(key);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 2, 3, 1, 1, 1};
        List<Integer> list = solution.topKFrequent(nums, 2);
        System.out.println(list);
    }
}
