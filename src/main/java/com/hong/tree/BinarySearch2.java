package com.hong.tree;

/**
 * @author Macrowang
 * @date 2019/03/08 18:39
 * 递归的二分查找法
 **/
public class BinarySearch2 {

    public static int find(Comparable[] arr, Comparable target) {
        return find(arr, 0, arr.length - 1, target);
    }

    public static int find(Comparable[] arr, int l, int r, Comparable target) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (target.compareTo(arr[mid]) == 0) return mid;
        if (target.compareTo(arr[mid]) > 0) {
            return find(arr, mid + 1, r, target);
        } else {
            return find(arr, l, mid - 1, target);
        }
    }
}
