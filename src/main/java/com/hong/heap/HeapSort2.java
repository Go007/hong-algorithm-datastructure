package com.hong.heap;

import com.hong.sort.SortTestHelper;

/**
 * @author wanghong
 * @date 2019/11/24 21:57
 *  优化的堆排序
 **/
public class HeapSort2 {

    // 对整个arr数组使用HeapSort2排序
    // HeapSort2, 借助我们的heapify过程创建堆
    // 此时, 创建堆的过程时间复杂度为O(n), 将所有元素依次从堆中取出来, 时间复杂度为O(nlogn)
    // 堆排序的总体时间复杂度依然是O(nlogn), 但是比HeapSort1性能更优, 因为创建堆的性能更优
    public static void sort(Integer[] arr){
        MaxHeap<Integer>  maxHeap = new MaxHeap<>(arr);
        for (int i = arr.length-1;i >= 0;i--){
            arr[i] = maxHeap.extractMax();
        }
    }

    // 测试 HeapSort2
    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray2(N, 0, 100000);
        SortTestHelper.testSort2("com.hong.heap.HeapSort2", arr);
    }
}
