package com.hong.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghong
 * @date 2019/10/14 22:02
 **/
public class VolatileDemo {
    /**
     * 1.验证volatile的可见性
     * 1.1 假如 int number = 0;没有volatile关键字修饰
     * 1.2 加上volatile，可以解决可见性问题
     *
     */
    public static void main(String[] args) {
        // 资源类
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);

        },"AA").start();

        while (myData.number == 0){
            // main线程一直在这里循环等待，直到number != 0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
