package com.hong.sort;

/**
 * <br>插入排序</br>
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找arr[i]合适的插入位置
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr,j,j-1);
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
        SortTestHelper.testSort("com.hong.sort.InsertionSort", arr);
    }
}
