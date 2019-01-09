package com.hong.sort;

import java.util.Arrays;

/**
 * <br>自底向上的归并排序</br>
 * 之前的归并排序中是自顶向下,扩散式逐步拆分成小的组,排好序后,再逐步向上收敛整合成一个最终有序的序列
 */
public class MergeSort3 {

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

    /**
     * Merge Sort Bottom Up 无优化版本
     *
     * @param arr
     */
    public static void sort0(int[] arr) {
        int n = arr.length;

        for (int sz = 1; sz < n; sz *= 2) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * Merge Sort Button Up优化
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int n = arr.length;

        // 对于小数组, 使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            InsertionSort.sort(arr, i, Math.min(i + 15, n - 1));
        }

        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
                if (arr[i + sz - 1] > arr[i + sz]) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.MergeSort3", arr);
    }

}
