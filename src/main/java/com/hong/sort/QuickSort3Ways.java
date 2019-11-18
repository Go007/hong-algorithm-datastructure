package com.hong.sort;

/**
 * <br>三路快速排序法</br>
 * 之前的快排中，都是把数组分成了两部分，<=v v >=v
 * 三路快排中， <v  ==v  >v
 * 三路快速排序处理 arr[l,r]
 * 将arr[l,r]分为<v ；==v ； >v 三部分
 * 之后递归对<v;>v两部分继续进行三路快排
 */
public class QuickSort3Ways {

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

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        int v = arr[l];
        int lt = l; // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1;

        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i] > v) {
                swap(arr, i, gt - 1);
                gt--;
            } else { // arr[i] == v
                i++;
            }
        }

        swap(arr, l, lt);
        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.QuickSort3Ways", arr);
    }
}
