package com.hong.sort;

import java.util.Arrays;

/**
 * 归并排序优化
 */
public class MergeSort2 {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int left, int right) {
        /**
         * 优化2：
         *对于小规模数组, 使用插入排序
         */
        if (right - left <= 15){
            InsertionSort.sort(arr,left,right);
            return;
        }

       // int mid = (left + right) / 2;
        int mid = left + (right - left)/2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        /**
         * 优化1
         *   对于arr[mid] <= arr[mid+1]的情况,不进行merge
         *   对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
         */
        if (arr[mid] > arr[mid+1]){
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.MergeSort2", arr);
    }

}
