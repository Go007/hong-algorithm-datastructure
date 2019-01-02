package com.hong.sort;

/**
 * <br>选择排序算法优化</br>
 * 在每一轮中, 可以同时找到当前未处理元素的最大值和最小值
 */
public class SelectionSort2 {

    public static void sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        int minIndex;
        int maxIndex;
        while (left < right) {
            minIndex = left;
            maxIndex = right;

            if (arr[minIndex] > arr[maxIndex]) {
                swap(arr, minIndex, maxIndex);
            }

            for (int i = left + 1; i < right; i++) {
                if (arr[i] < arr[minIndex]){
                    minIndex = i;
                }else if (arr[i] > arr[maxIndex]){
                    maxIndex = i;
                }
            }

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);

            left++;
            right--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.hong.sort.SelectionSort2", arr);
    }

}
