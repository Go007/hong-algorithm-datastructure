package com.hong.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by John on 2019/1/24.
 * wait...notify/notifyAll机制存在类似死锁的问题。
 * 当需要被唤醒的子线程执行时间过长，而主调线程先一步执行了notifyAll方法
 * 那么子线程将永远处于等待中而无法被唤醒。（先后顺序不同）
 * 而LockSupport.park()和LockSupport.unpark()可以实现顺序无关性，即使主调线程先一步执行了park()方法,
 * 子线程还是可以被唤醒继续后面的流程。
 */
public class LockParkTest {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread() + "等待");
                LockSupport.park();
                System.out.println(Thread.currentThread() + "等待结束，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        //在主线程中，3s之后唤醒
        Thread.sleep(3000L);

        LockSupport.unpark(t);
        System.out.println(Thread.currentThread() + "唤醒等待者");
        Thread.sleep(10000000L);
    }

}
