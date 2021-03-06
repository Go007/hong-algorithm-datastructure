package com.hong.sort;

/**
 * <br>插入排序改进</br>
 */
public class InsertionSort2 {

    /**
     * 改进策略:
     * 在之前的排序中,我们每次从第i个元素开始,向后递减,依次遍历相邻的两个元素大小,符合条件就交换位置,这样一轮比较后arr[i]就放到了合适的位置.
     * 现在在每轮比较前,先将arr[i]的值拷贝一份出来,然后将这个拷贝的值temp依次与它前面的元素x比较，若temp < x，则直接将x覆盖x的后一个位置的元素，
     * 直到找到temp >= y,停止循环比较，并将temp直接覆盖y的后一个位置的元素。
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i;
            for (; j > 0 && temp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            // 经过上面的遍历比较后,j已经是arr[i]的正确插入位置了,直接替换这个位置下标对应的值
            arr[j] = temp;
        }
    }
    public static void main(String[] args) {
        int N = 20000;
        int[] arr = SortTestHelper.generateRandomArray(N, 1, 100000);
        SortTestHelper.testSort("com.hong.sort.InsertionSort2", arr);
    }
}
