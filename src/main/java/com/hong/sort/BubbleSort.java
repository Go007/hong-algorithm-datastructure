package com.hong.sort;

/**
 * <br>冒泡排序</br>
 */
public class BubbleSort {

    /**
     * 把小的元素往前调或者把大的元素往后调
     *
     * @param arr
     */
    public static void sort0(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 经测试,这种倒序索引排序比上面的要慢
     * @param arr
     */
    public static void sort1(int[] arr) {
        int n = arr.length;
        for (int i = n; i > 0; i--) {
            for (int j = n - 1; j > n - i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        boolean swap;
        do {
            swap = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swap = true;
                }
            }
            /**
             *  每一趟Bubble Sort都将最大的元素放在了最后的位置
             *  所以下一次排序, 最后的元素可以不再考虑
             */
            n--;
        } while (swap);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.BubbleSort", arr);
    }

}
