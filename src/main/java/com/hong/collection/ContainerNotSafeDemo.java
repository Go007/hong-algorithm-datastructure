package com.hong.collection;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wanghong
 * Date 2019-10-15 10:53
 * Description: 集合类不安全的问题
 * java.util.ConcurrentModificationException
 *
 * 不要只是会用？会用只不过是一个API调用工程师？
 * 底层原理？
 *
 * 导致原因：
 * 并发争抢修改导致。
 *
 * 如何解决？
 * 1. new Vector<>()
 * 2. Collections.synchronizedList(new ArrayList<>());
 * 3. new CopyOnWriteArrayList<>()
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
       // List<String> list = new ArrayList<>();
        // 写时复制
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i=0;i<30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
