package com.hong.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by John on 2019/1/24.
 * 自己实现一个FutureTask
 * 关键步骤：
 * 1.泛型
 * 2.构造方法传入Callable
 * 3.实现Runnable接口
 * 4.get()方法返回执行结果
 * 5.任务未执行结束的话，get()方法有阻塞的效果，等待任务执行完成
 */
public class MyFutureTask<T> implements Runnable {

    /**
     * 执行状态标识
     */
    private String state = "NEW";

    /**
     * 包装业务代码
     */
    private Callable<T> callable;

    /**
     * 执行结果
     */
    private T result;

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    public T get() {
        if ("END".equals(state)) {
            return result;
        }

        // 阻塞等待任务执行完成
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread() + "进入等待");
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            //捕获异常
            state = "EX";
        } finally {
            state = "END";
        }

        /**
         * wait...notify/notifyAll一定要放在synchronized中
         */
        synchronized (this) {
            System.out.println(Thread.currentThread() + "唤醒等待的线程");
            this.notifyAll();
        }

    }
}
