package com.hong.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/16 11:16
 * @Version V1.0
 *  多线程下的 i++ 操作
 **/
public class CounterLock {

    public int i;

    private Lock lock;

    public CounterLock(){
        this.lock = new ReentrantLock();
    }

    public CounterLock(Lock lock){
        this.lock = lock;
    }

    public void increment(){
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
        }
    }

}
