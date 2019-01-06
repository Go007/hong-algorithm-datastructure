package com.hong.sort;

import java.util.Arrays;

/**
 * 归并排序 O(Nlog(N))
 * 需要将当前的数组拷贝一份出来，因此相比之前的排序，需要更多的存储空间
 */
public class MergeSort {

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * @param arr
     * @param l 左半部元素起始点
     * @param mid 左半部元素终点
     * @param r 右半部元素终点
     */
    public static void merge(int[] arr,int l,int mid,int r) {
        int[] aux = Arrays.copyOfRange(arr,l,r+1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l;
        int j = mid + 1;
        for (int k = l;k <= r;k++){
            // 如果左半部分元素已经全部处理完毕
            if (i > mid){
                arr[k] = aux[j-1];
                j++;
            }
            // 如果右半部分元素已经全部处理完毕
            else if (j > r){
                arr[k] = aux[i-1];
                i++;
            }
            // 左半部分所指元素 < 右半部分所指元素
            else if (aux[i-1] < aux[j-1]){
                arr[k] = aux[i-1];
                i++;
            }
            // 左半部分所指元素 >= 右半部分所指元素
            else {
                arr[k] = aux[j-l];
                j ++;
            }
        }
    }

    /**
     * 递归使用归并排序,对arr[l...r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sort(int[] arr,int l,int r){
        if (l >= r){
            return;
        }

        int mid = (l + r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void sort(int[] arr){
        int n = arr.length;
        // [0,n-1]，这里注意区间的开闭
        sort(arr, 0, n-1);
    }

    // 测试MergeSort
    public static void main(String[] args) {
        // Merge Sort O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
        int N = 10000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.MergeSort", arr);
    }
}
