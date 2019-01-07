package com.hong.sort;

/**
 * <br>插入排序</br>
 * 对比选择排序 com.hong.sort.SelectionSort
 * 插入排序可以提前终止比较，而选择排序为了每次在剩下的元素中找到最小值，
 * 不得不把当前元素与剩下未排序的元素挨个比较，没有提前终止的机会，
 * 所以理论上插入排序的效率要高于选择排序。
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        /**
         * 这里的i从1开始，因为插入排序的思想是把当前元素以此和它前面
         * 已排好序的元素做比较,第0个元素前面没有元素，所以直接从第1个开始。
         */
        for (int i = 1; i < n; i++) {
            // 寻找arr[i]合适的插入位置
            /*for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr,j,j-1);
            }*/

            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    /**
                     * 因为插入排序当前元素前面的元素都是排好序的，
                     * 所以一旦发现当前元素>当前元素的前一个元素，就可以直接停止比较，继续下一个
                     */
                    break;
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 对arr[l...r]的区间使用InsertionSort排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int e = arr[i];
            int j = i;
            for (; j > l && arr[j - 1] > e; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.InsertionSort", arr);
    }
}
