package com.hong.sort;

/**
 * Created by John on 2019/1/6.
 */
public class ShellSort2 {

    public static void sort(int[] arr) {
        int n = arr.length;
        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;
        while (h < n/3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                int temp = arr[i];
                int j = i;
                for ( ; j >= h && arr[j-h] > temp; j -= h) {
                    arr[j] = arr[j-h];
                }
                arr[j] = temp;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.ShellSort2", arr);
    }
}
