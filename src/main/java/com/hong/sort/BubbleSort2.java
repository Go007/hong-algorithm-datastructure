package com.hong.sort;

/**
 * <br>冒泡排序优化</br>
 */
public class BubbleSort2 {

    /**
     * 按照之前升序排列,每轮循环将剩下元素中最大的元素调到最后.
     * 考虑这样的情况:假设在前面未排序的元素中,后面靠近尾端的元素已经是有序的了,
     * 但是之前的做法还是会每次把这些元素包括进去遍历.
     * 例如:原始一组数据: 3,6,2,4,5
     * 第一轮排序后:3,2,4,5,6
     * 按照之前的思想:接下来的一轮回会在剩下的未排序序列 3,2,4,5中找出最大的元素调到最后,
     * 但是4,5本身是有序的,他们在上一轮的遍历中并没有交换位置,所以我们完全可以依据上一轮的遍历设置个记录
     * 最后交换顺序的指针,然后只需要遍历这个指针之前的元素.
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        int lastSwapIndex;
        do {
            //重置,清零
            lastSwapIndex = 0;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i+1]){
                    swap(arr,i,i+1);
                    // 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑,注意这里的临界点
                    lastSwapIndex = i+1;
                }
            }
            n = lastSwapIndex;
        } while (lastSwapIndex > 0);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.BubbleSort2", arr);
    }

}
