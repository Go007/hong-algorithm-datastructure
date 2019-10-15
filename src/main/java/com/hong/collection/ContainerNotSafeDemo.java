package com.hong.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wanghong
 * Date 2019-10-15 10:53
 * Description: 集合类不安全的问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // java.util.ConcurrentModificationException
        for (int i=0;i<30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
