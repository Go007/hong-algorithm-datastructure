package com.hong.other;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wanghong
 * Date 2020-01-08 14:26
 * Description:
 * java集合遍历删除指定元素异常分析总结
 * https://www.cnblogs.com/cowboys/p/9313264.html
 */
public class CollectionTest {

    private static int count = 0;
    private final static CountDownLatch cdl = new CountDownLatch(100);

    public static void main(String[] args) {
        // 集合转数组 toArray(T[] array)
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] arr = list.toArray(new String[list.size()]);
        for (String s : arr) {
            System.out.print(s + ",");
        }
        System.out.println();
        // 数组转集合
        String[] str = {"d", "e"};
        List<String> ll = Arrays.asList(str); // 适配器模式
        System.out.println(ll);
        // ll.add("f");//java.lang.UnsupportedOperationException
        str[0] = "f";
        System.out.println(ll);

        // 集合的删除
        for (String s:list){
            if ("c".equals(s)){ //java.util.ConcurrentModificationException,删除尾元素时
                list.remove(s);
            }
        }

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if ("b".equals(next)) {
                it.remove();
            }
        }

        System.out.println(list);
        System.out.println("=========");
        // 上面使用迭代器删除在单线程环境下是没有问题的，如果是多线程呢？

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Iterator<String> itt = list.iterator();
                while (itt.hasNext()) {
                    String next = itt.next();
                    if ("b".equals(next)) {
                        itt.remove();
                    }
                }
                count++;
                System.out.println(list.size());
            }).start();

            cdl.countDown();
        }

        System.out.println(count);

        Map<String,String> map = new HashMap<>(16);
        map.put("a","a");
        map.put("b","b");
        map.forEach((k,v) -> System.out.println(k + "->" +v)); //JDK8
        // < JDK8
        for (Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }
}