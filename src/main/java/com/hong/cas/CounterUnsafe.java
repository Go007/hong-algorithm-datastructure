package com.hong.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/16 14:44
 * @Version V1.0
 *  使用 Unsafe 保证 多线程下的 i++ 操作的线程安全性
 **/
public class CounterUnsafe {
    //public static volatile int i; // 不能使用 static 修饰 ，java.lang.IllegalArgumentException
    public volatile int i;
    private static Unsafe unsafe;
    private static long valueOffset;

    static {
       // unsafe = Unsafe.getUnsafe(); java.lang.SecurityException: Unsafe
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);

            Field iField = CounterUnsafe.class.getDeclaredField("i");
            valueOffset = unsafe.objectFieldOffset(iField);
        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

    }

    public void increment(){
        for (;;){
            int current = unsafe.getIntVolatile(this,valueOffset);
            if (unsafe.compareAndSwapInt(this,valueOffset,current,current+1)){
                break;
            }
        }
    }

}

