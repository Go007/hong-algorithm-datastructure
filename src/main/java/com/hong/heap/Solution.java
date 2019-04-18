package com.hong.heap;

import java.util.*;

/**
 * @author wanghong
 * @date 2019/04/17 9:57
 *
 * 优先队列的经典问题：
 * 在N个元素中选出前M个元素
 *  M远小于N
 *  排序： NlongN
 *  优先队列 NlogM   维护当前看到的前M个元素
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
            /*if (this.freq < o.freq) {
                return 1;
            } else if (this.freq > o.freq) {
                return -1;
            } else {
                return 0;
            }*/

            if (this.freq < o.freq) {
                return -1;
            } else if (this.freq > o.freq) {
                return 1;
            } else {
                return 0;
            }
        }

        public int getE() {
            return e;
        }

        public void setE(int e) {
            this.e = e;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
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

        // 这里因为自定义了比较规则，实际内部是个最小堆
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
        while (!heap.isEmpty()) {
            res.add(heap.extractMax().e);
        }

        return res;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 桶排序,每个数组里面是一个List
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<>();
            }
            bucket[value].add(entry.getKey());
        }

        List<Integer> res = new LinkedList<>();
        for (int j = bucket.length - 1; j >= 0; j--) {
            List<Integer> list = bucket[j];
            if (list == null) {
                continue;
            }
            for (int n : list) {
                if (res.size() == k) {
                    break;
                }
                res.add(n);
            }
        }

        return res;
    }

    /**
     * 使用优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // JDK中的PriorityQueue默认是按最小堆来的
        //  PriorityQueue<Freq> queue = new PriorityQueue<>();
        // 如果Freq没有实现Comparable接口，则可以显示的向队列中传递自定义比较器
        PriorityQueue<Freq> queue = new PriorityQueue<>(Comparator.comparingInt(Freq::getFreq));
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(new Freq(key, map.get(key)));
            } else if (map.get(key).compareTo(queue.peek().freq) > 0) {
                queue.remove();
                queue.add(new Freq(key, map.get(key)));
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll().e);
        }

        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        List<Integer> list = solution.topKFrequent2(nums, 2);
        System.out.println(list);
    }
}
