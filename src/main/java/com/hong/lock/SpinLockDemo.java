package com.hong.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wanghong
 * Date 2019-10-16 10:24
 * Description:
 * 实现一个自旋锁
 * 自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock()f方法自己持有锁5s，
 * B随后进来发现已经有线程持有锁，不是null，所以只能等待，直到A释放锁B随后抢到。
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\tcome in");

        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\tinvoked myUnLock()");
    }

    public static void main(String[] args) {
        /**
         * A线程进来，发现期望的是空的，那么while的条件就是false，于是不进入循环，直接拿到了锁。
         * B线程进来，发现期望的值不是空，那么while的条件就是true，于是它进入锁中，一直会循环的判断，直到期望的值是空了，才能推出循环，获得锁。
         */
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"B").start();
    }

}
