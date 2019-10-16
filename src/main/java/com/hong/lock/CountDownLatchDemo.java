package com.hong.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author wanghong
 * @date 2019/10/16 21:33
 * 减法，倒计时
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室");
                cdl.countDown();
            },String.valueOf(i)).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t***班长最后关门走人");
    }
}
