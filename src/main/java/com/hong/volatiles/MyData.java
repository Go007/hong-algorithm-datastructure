package com.hong.volatiles;

/**
 * @author wanghong
 * @date 2019/10/14 22:01
 **/
public class MyData {
    volatile int number = 0;

    public void add(){
        this.number = 60;
    }
}
