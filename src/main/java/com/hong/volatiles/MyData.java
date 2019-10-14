package com.hong.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanghong
 * @date 2019/10/14 22:01
 **/
public class MyData {
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void add(){
        this.number = 60;
    }

    // 注意，volatile不保证原子性
    public void addPlusPlus(){
        number++;
    }

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
