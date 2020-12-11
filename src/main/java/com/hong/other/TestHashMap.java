package com.hong.other;

import java.util.HashMap;

/**
 * @author ：wanghong
 * @date ：2020-12-11 09:21
 * @description：
 */
public class TestHashMap {

    public static void main(String[] args) {
        /**
         * HashMap初始化后，默认值不变，在不扩容的情况下，最多可以存入多少元素？
         */
        //第一种情况：一直往同一个 table[bucketIndex]中放入元素，当放入第9个元素时，触发树形转换,扩容
        HashMap<MyObj,Integer> map = new HashMap<>();
        for (int i = 1;i <= 9;i++){
            map.put(new MyObj(),i);
        }
        System.out.println(map.size());

        // 第二种情况：依次往table中12个不同的bucket中放入数据，当放入第13个元素时，触发 ++size > threshold,扩容
        HashMap<Integer,Integer> map2 = new HashMap<>();
        for (int j = 1;j <= 13;j++){
            map2.put(j,j);
        }
        System.out.println(map2.size());
    }

    private static class MyObj{

        @Override
        public int hashCode() {
            return 15;
        }
    }
}
