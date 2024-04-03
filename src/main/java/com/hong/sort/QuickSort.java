package com.hong.sort;

/**
 * <br>快速排序</br>
 * <p>
 * 快速排序是不稳定的算法
 */
public class QuickSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    /**
     * 递归使用快速排序,对arr[l...r]的范围进行排序
     *
     * @param arr
     */
    public static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition2(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 分区.对arr[l...r]部分进行partition操作
     * 返回p,使得arr[l...p-1] < arr[p] < arr[p+1...r]
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition2(int[] arr, int l, int r) {
        // 将第一个元素值作为临界点
        int v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                /**
                 * 从第一个元素开始依次与临界点值比较,若发现 arr[i]< 临界点,
                 * 则j向前一位,则arr[j]就是当前第一个>v的值,交换i,j.
                 */
                swap(arr, i, ++j);
            }
        }
        //arr[l+1...j] < v < arr[j+1...i)
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.QuickSort", arr);
    }

}
