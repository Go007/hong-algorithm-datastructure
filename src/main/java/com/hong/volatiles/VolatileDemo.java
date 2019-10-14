package com.hong.volatiles;

import com.sun.deploy.security.MozillaMyKeyStore;

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
       /* new Thread(() -> {
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
        System.out.println(Thread.currentThread().getName() + "\t mission is over");*/

        for (int i=0;i<20;i++){
            new Thread(() -> {
                for (int j=1;j<=1000;j++){
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        // 等待上面20个线程全部计算完成后，再用main线程取得最终的结果看值是多少？
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type,finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type,finally number value:" + myData.atomicInteger);
    }
}
