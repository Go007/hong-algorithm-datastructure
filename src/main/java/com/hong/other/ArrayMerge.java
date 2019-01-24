package com.hong.other;

import com.hong.sort.QuickSort;
import com.hong.sort.SortTestHelper;

/**
 * Created by John on 2019/1/24.
 */
public class ArrayMerge {
    public static void main(String[] args) {
        int[] arr1 = SortTestHelper.generateRandomArray(20, 1, 100);
        int[] arr2 = SortTestHelper.generateRandomArray(15, 1, 100);
        QuickSort.sort(arr1);
        QuickSort.sort(arr2);
        for (int i:arr1){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i:arr2){
            System.out.print(i + " ");
        }
        System.out.println();
        int[] result = merge(arr1, arr2);
        for (int i:result){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(SortTestHelper.isSorted(result));
    }

    /**
     * 将两个有序数组合并为一个有序数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int l = 0;
        int r = 0;
        int k = 0;
        int[] result = new int[arr1.length + arr2.length];
        /**
         * 按位循环比较两个数组，较小元素的放入新数组，
         * 下标加一（注意，较大元素对应的下标不加一），直到某一个下标等于数组长度时退出循环
         */
        while (l < arr1.length && r < arr2.length) {
            if (arr1[l] <= arr2[r]) {
                result[k++] = arr1[l++];
            } else {
                result[k++] = arr2[r++];
            }
        }

        /**
         *  后面两个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入
         *  此时较短数组已经全部放入新数组，较长数组还有部分剩余，最后将剩下的部分元素放入新数组，大功告成
         */
        while (l < arr1.length) {
            result[k++] = arr1[l++];
        }

        while (r < arr2.length) {
            result[k++] = arr2[r++];
        }

        return result;
    }
}
