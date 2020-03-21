package com.hong.singleton;

/**
 * @author wanghong
 * @date 2020/03/21 17:57
 **/
public class Singleton0 {

    // 设置标志位，表示构造函数是否为第一次调用
    private static volatile boolean hasNew = false;

    private Singleton0(){
        // 加锁防止并发
        synchronized (Singleton0.class){
            // 非首次调用，直接抛出异常
            if (hasNew){
                throw new RuntimeException("单例模式被破坏");
            }
            // 首次掉哟个，更新标志位
            hasNew = true;
            System.out.println("触发私有构造器。。。");
        }
    }

    private static class Holder{
        private static Singleton0 instance = new Singleton0();
    }

    public static Singleton0 getInstance(){
        return Holder.instance;
    }
}
