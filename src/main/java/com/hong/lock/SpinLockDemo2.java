package com.hong.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/16 17:32
 * @Version V1.0
 *  使用 CAS + 队列 实现一个简单的自旋锁
 *
 *  wait/notify
 *  condition
 *  suspend/resume
 *  park/unpark
 **/
public class SpinLockDemo2 implements Lock {

    // 锁的拥有者
    private AtomicReference<Thread> owner = new AtomicReference<>();

    // 等待队列
    private LinkedBlockingQueue<Thread> waiter = new LinkedBlockingQueue<>();

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null,Thread.currentThread());
    }

    @Override
    public void lock() {
        if (!tryLock()){
            waiter.offer(Thread.currentThread());
            for (;;){
                Thread head = waiter.peek();
                if (head == Thread.currentThread()){
                    if (!tryLock()){
                        // 挂起线程
                        LockSupport.park();
                    }else {
                        // 抢锁成功,线程出队
                        waiter.poll();
                        return;
                    }
                }else {
                    LockSupport.park();
                }
            }
        }
    }

    public boolean tryUnlock(){
        // 首先判断当前线程是否持有锁
        if (owner.get() != Thread.currentThread()){
            throw new IllegalStateException();
        }

        return owner.compareAndSet(Thread.currentThread(),null);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public void unlock() {
        if (tryUnlock()){
            Thread th = waiter.peek();
            if (th != null){
                LockSupport.unpark(th);
            }
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
