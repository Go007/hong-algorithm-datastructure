package com.hong.sort;

/**
 * <br>选择排序</br>
 * O(n^2)
 */
public class SelectionSort {

    /**
     * 选择排序思想：
     * 从第一个位置开始，找到后面最小的元素，然后与第一个位置的元素交换，
     * 这样，第一个位置的元素就是最小的元素了；
     * 然后从第二个位置开始，依次重复上面的步骤。
     * 两层for循环：
     * 外层循环：控制比较的轮数
     * 内层循环：寻找剩下元素中的最小元素
     * @param arr
     */
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
