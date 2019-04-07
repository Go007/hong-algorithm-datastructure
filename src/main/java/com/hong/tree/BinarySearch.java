package com.hong.tree;

/**
 * @author Macrowang
 * @date 2019/03/08 14:55
 * 非递归二分查找法
 **/
public class BinarySearch {

    /**
     * 在有序数组中arr中查找target
     * 找到则返回相应得索引index，否则返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int find(Comparable[] arr, Comparable target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            //int mid = (l + r)/2;
            // 防止极端情况下得整形溢出
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
