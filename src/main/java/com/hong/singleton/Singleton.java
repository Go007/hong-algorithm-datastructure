package com.hong.singleton;

import java.io.Serializable;

/**
 * @author wanghong
 * @date 2020/03/21 17:57
 **/
//一个类只有实现了Serializable接口，它的对象才是可序列化的
public class Singleton implements Serializable {
    // 设置标志位，表示构造函数是否为第一次调用
    private static volatile boolean hasNew = false;

    private Singleton() {
        // 加锁防止并发
        synchronized (Singleton.class) {
            // 非首次调用，直接抛出异常
            if (hasNew) {
                throw new RuntimeException("单例模式被破坏");
            }
            // 首次掉哟个，更新标志位
            hasNew = true;
            System.out.println("触发私有构造器。。。");
        }
    }

    private static class Holder {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return Singleton.Holder.instance;
    }

    //阻止序列化破坏单例
    private Object readResolve(){
        return Holder.instance;
    }
}
