package com.hong.recursion;

/**
 * @author Macrowang
 * @date 2019/04/08 15:14
 *
 * 递归的基础与递归的宏观语义
 **/
public class Sum {

    /**
     *  计算[l,r]区间内的元素总和
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int sum(int[] arr,int l,int r){
        if (l >= arr.length){
            return 0;
        }

        if (r >= arr.length){
            r = arr.length;
        }

        return arr[l] + sum(arr,l+1,r);
    }

    public static int sum(int[] arr){
        return sum(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(sum(arr));
    }
}
