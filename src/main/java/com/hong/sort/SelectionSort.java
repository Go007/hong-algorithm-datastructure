package com.hong.sort;

/**
 * <br>选择排序</br>
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        int minIndex;
        for (int i = 0; i < n-1; i++) {
            // 寻找[i,n)区间里的最小索引
            minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            // 将每轮比较的较小值与当前值交换
            if (minIndex != i){
                swap(arr,i,minIndex);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.SelectionSort", arr);
    }
}
