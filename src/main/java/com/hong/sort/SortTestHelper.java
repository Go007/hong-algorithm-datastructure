package com.hong.sort;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 排序算法测试辅助类
 */
public class SortTestHelper {

    private SortTestHelper() {
    }

    /**
     * 生成n个元素的随机数组,每个元素的随机范围为[leftRange,rightRange]
     *
     * @param n
     * @param leftRange
     * @param rightRange
     * @return
     */
    public static int[] generateRandomArray(int n, int leftRange, int rightRange) {
        assert leftRange <= rightRange;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = leftRange + (int) Math.random() * (rightRange - leftRange + 1);
        }
        return arr;
    }

    /**
     * 打印arr数组的所有内容
     * @param arr
     */
    public static void printArray(Object[] arr){
        Arrays.asList(arr).forEach(e -> {
            System.out.print(e);
            System.out.print(' ');
        });
        System.out.println("============================");
    }

    /**
     *  判断arr数组是否有序(升序)
      * @param arr
     * @return
     */
    public static boolean isSorted(int[] arr){

        for( int i = 0 ; i < arr.length - 1 ; i ++ ){
            if( arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
     * @param sortClassName
     * @param arr
     */
    public static void testSort(String sortClassName, int[] arr){
        /**
         * Java的反射机制，通过排序的类名，运行排序函数
         */
        try{
            Class sortClass = Class.forName(sortClassName);
            Method sortMethod = sortClass.getMethod("sort",new Class[]{int[].class});
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null,params);
            long endTime = System.currentTimeMillis();

            assert isSorted( arr );
            System.out.println(isSorted(arr));
            System.out.println( sortClass.getSimpleName()+ " : " + (endTime-startTime) + "ms" );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
