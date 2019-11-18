package com.hong.sort;

/**
 * <br>双路快速排序法</br>
 * 在之前的快排中，对于与给定的标定点相同的重复元素都是放到了一边，
 * 这样会导致某一边会有可能有大量重复元素；
 * 为了解决这个弊端，双路快排会尽可能平均的将重复元素放到标定点的两边
 */
public class QuickSort2Ways {

    public static int partition(int[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        int v = arr[l];
        // arr[l+1...i) <= v ; arr(j...r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            while (i<= r && arr[i] < v) {
                i++;
            }
            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            while (j >= l+1 && arr[j] > v) {
                j--;
            }

            if (i > j) {
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


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
        // 对于小规模数组, 使用插入排序
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.QuickSort2Ways", arr);
    }
}
