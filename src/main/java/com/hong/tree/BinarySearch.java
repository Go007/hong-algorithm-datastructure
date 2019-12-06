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

    /**
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回第一个target相应的索引index
     * 如果没有找到target, 返回比target小的最大值相应的索引, 如果这个最大值有多个, 返回最大索引
     * 如果这个target比整个数组的最小元素值还要小, 则不存在这个target的floor值, 返回-1
     * @param arr
     * @param target
     * @return
     */
    public static int floor(Comparable[] arr,Comparable target){
        // 寻找比target小的最大索引
        int l = -1;
        int r = arr.length - 1;
        while (l < r){
            // 使用向上取整避免死循环
            int mid = l + (r-l+1)/2;
            if (arr[mid].compareTo(target) >= 0){
                r = mid-1;
            }else {
                l = mid;
            }
        }

        // 如果该索引+1就是target本身, 该索引+1即为返回值
        if (l + 1 < arr.length && arr[l+1] == target){
            return l+1;
        }

        return l;
    }

    /**
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回最后一个target相应的索引index
     * 如果没有找到target, 返回比target大的最小值相应的索引, 如果这个最小值有多个, 返回最小的索引
     * 如果这个target比整个数组的最大元素值还要大, 则不存在这个target的ceil值, 返回整个数组元素个数n
     * @param arr
     * @param target
     * @return
     */
    public static int ceil(Comparable[] arr, Comparable target){
        // 寻找比target大的最小索引
        int l = 0;
        int r = arr.length;
        while (l < r){
            // 使用普通的向下取整即可避免死循环
            int mid = l + (r-l)/2;
            if (arr[mid].compareTo(target) <= 0){
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        // 如果该索引-1就是target本身, 该索引+1即为返回值
        if( r - 1 >= 0 && arr[r-1] == target )
            return r-1;

        // 否则, 该索引即为返回值
        return r;
    }

    // 测试我们用二分查找法实现的floor和ceil两个函数
    // 请仔细观察在我们的测试用例中，有若干的重复元素，对于这些重复元素，floor和ceil计算结果的区别：）
    public static void main(String[] args) {
        Integer arr[] = new Integer[]{1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 5, 5, 5, 6, 6, 6};
        for (int i = 0; i <= 8; i++) {
            int floorIndex = floor(arr, i);
            System.out.println("the floor index of " + i + " is " + floorIndex + ".");
            if (floorIndex >= 0 && floorIndex < arr.length)
                System.out.println("The value is " + arr[floorIndex] + ".");
            System.out.println();

            int ceilIndex = ceil(arr, i);
            System.out.println("the ceil index of " + i + " is " + ceilIndex + ".");
            if (ceilIndex >= 0 && ceilIndex < arr.length)
                System.out.println("The value is " + arr[ceilIndex] + ".");
            System.out.println();

            System.out.println();
        }
    }

}
