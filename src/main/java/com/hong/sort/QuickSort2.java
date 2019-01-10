package com.hong.sort;

/**
 * <br>随机化快速排序法</br>
 *
 */
public class QuickSort2 {
    /**
     * 分区.对arr[l...r]部分进行partition操作
     * 返回p,使得arr[l...p-1] < arr[p] < arr[p+1...r]
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition(int[] arr, int l, int r) {
        /**
         * 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
         */
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        int v = arr[l];
        int j = l;
        for (int i = l; i <= r; i++) {
            if (arr[i] < v){
                j++;
                swap(arr,i,j);
            }
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
        sort(arr, 0, n-1);
    }

    /**
     * 递归使用快速排序,对arr[l...r]的范围进行排序
     * @param arr
     */
    public static void sort(int[] arr, int l, int r) {
        // 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p-1 );
        sort(arr, p+1, r);
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.QuickSort2", arr);
    }

}
