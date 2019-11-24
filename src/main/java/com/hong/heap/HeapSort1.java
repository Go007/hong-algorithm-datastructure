package com.hong.heap;

import com.hong.sort.SortTestHelper;

/**
 * @author wanghong
 * @date 2019/11/24 21:50
 *  基础堆排序和Heapify(堆化)
 **/
public class HeapSort1 {

    // 对整个arr数组使用HeapSort1排序
    // HeapSort1, 将所有的元素依次添加到堆中, 在将所有元素从堆中依次取出来, 即完成了排序
    // 无论是创建堆的过程, 还是从堆中依次取出元素的过程, 时间复杂度均为O(nlogn)
    // 整个堆排序的整体时间复杂度为O(nlogn)
    public static void sort(int[] arr){
        int n = arr.length;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(n);
        for (int i = 0;i < n;i++){
            maxHeap.add(arr[i]);
        }
        for (int i = n-1;i>=0;i--){
            arr[i] = maxHeap.extractMax();
        }
    }

    // 测试 HeapSort1
    public static void main(String[] args) {
        int N = 1000000;
        int[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.hong.heap.HeapSort1", arr);
    }
}
