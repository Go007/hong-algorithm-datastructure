package com.hong.sort;

/**
 * <br>希尔排序</br>
 * 缩小增量排序
 */
public class ShellSort {

    public static void sort(int[] a) {
        int d = a.length / 2 ;
        while (d >=1) {
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i = i + d) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            d = d / 2;
        }
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.ShellSort", arr);
    }

}
