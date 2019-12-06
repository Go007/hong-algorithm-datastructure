package com.hong.tree;

import java.util.Arrays;

/**
 * Created by wanghong
 * Date 2019-12-06 10:47
 * Description:
 */
public class LinearSearch {
    // 线性查找法, 实现lower_bound
    // 即在一个有序数组arr中, 寻找大于等于target的元素的第一个索引
    // 如果存在, 则返回相应的索引index
    // 否则, 返回arr的元素个数 n
    public static int lower_bound(Comparable[] arr, Comparable target) {

        if (arr == null)
            throw new IllegalArgumentException("arr can not be null.");

        for (int i = 0; i < arr.length; i++)
            if (arr[i].compareTo(target) >= 0)
                return i;

        return arr.length;
    }

    // 线性查找法, 实现upper_bound
    // 即在一个有序数组arr中, 寻找大于target的元素的第一个索引
    // 如果存在, 则返回相应的索引index
    // 否则, 返回arr的元素个数 n
    public static int upper_bound(Comparable[] arr, Comparable target) {

        if (arr == null)
            throw new IllegalArgumentException("arr can not be null.");

        for (int i = 0; i < arr.length; i++)
            if (arr[i].compareTo(target) > 0)
                return i;

        return arr.length;
    }

    private static Integer[] generateRandomOrderedArray(int n, int rangeL, int rangeR) {

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1)) + rangeL;
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {

        int n = 1000;
        int m = 100;
        Integer[] arr = generateRandomOrderedArray(n, 0, m);

        /// 我们使用简单的线性查找法来验证我们写的二分查找法
        for (int i = -1; i <= m + 1; i++) {

            if (BinarySearch.lowerBound(arr, i) != LinearSearch.lower_bound(arr, i))
                throw new IllegalArgumentException("lower_bound error!");

            if (BinarySearch.upperBound(arr, i) != LinearSearch.upper_bound(arr, i))
                throw new IllegalArgumentException("upper_bound error!");
        }

        System.out.println("test completed:)");
    }
}
