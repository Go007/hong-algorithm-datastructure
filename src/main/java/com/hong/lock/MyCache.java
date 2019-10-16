package com.hong.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wanghong
 * Date 2019-10-16 10:44
 * Description:
 *  读写锁
 */
public class MyCache {

    private volatile Map<String,Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwl.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch(Exception e){

        }finally {
            rwl.writeLock().unlock();
        }
    }

    public void get(String key){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取。。。");
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        }catch (Exception e){

        }finally {
            rwl.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i=0;i<5;i++){
            final int temp = i;
            new Thread(() ->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i=0;i<5;i++){
            final int temp = i;
            new Thread(() ->{
                myCache.get(temp + "");
            },String.valueOf(i)).start();
        }
    }

}
