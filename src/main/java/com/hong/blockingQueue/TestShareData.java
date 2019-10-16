package com.hong.blockingQueue;

/**
 * @author wanghong
 * @date 2019/10/16 22:44
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加一，一个减一，来5轮
 **/
public class TestShareData {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.increment();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "BB").start();
    }
}
