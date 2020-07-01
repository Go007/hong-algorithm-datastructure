package com.hong.other;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/7/1 10:06
 * @Version V1.0
 * 并发编程：分工，同步，互斥
 **/
public class JoinExample {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println("t1 执行完毕");
            }
        },"Thread-1");

        Thread t2 = new Thread(() -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println("t2 执行完毕");
            }
        },"Thread-2");

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println("main线程执行完毕");
    }
}
