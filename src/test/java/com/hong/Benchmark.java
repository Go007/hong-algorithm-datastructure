package com.hong;

import com.hong.lock.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wanghong
 * @date 2019/07/25 15:46
 * 压测工具类
 **/
public class Benchmark {

    private int CONCURRENCY_LEVEL;

    private BenchmarkCallback benchmarkCallback;

    public Benchmark(BenchmarkCallback benchmarkCallback){
        this(100,benchmarkCallback);
    }

    public Benchmark(int CONCURRENCY_LEVEL, BenchmarkCallback benchmarkCallback) {
        this.CONCURRENCY_LEVEL = CONCURRENCY_LEVEL;
        this.benchmarkCallback = benchmarkCallback;
    }

//    public void test0() {
////        long start = System.currentTimeMillis();
////        final CountDownLatch cdl = new CountDownLatch(1);
////        for (int i = 0; i < CONCURRENCY_LEVEL; i++) {
////            Thread thread = new Thread(() -> {
////                try {
////                    cdl.await();
////                    benchmarkCallback.task();
////                    System.out.println(Thread.currentThread().getName() + "执行");
////                } catch (Exception e) {
////                    System.out.println(Thread.currentThread().getName() + "线程执行出现异常:" + e.getMessage());
////                }
////            },"Thread-Benchmark-" + i);
////
////            thread.start();
////
////        }
////        cdl.countDown();
////        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
////    }


    public void test() throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(CONCURRENCY_LEVEL);

        for (int i = 0; i < CONCURRENCY_LEVEL; i++) {
            Thread thread = new Thread(() -> {
                try {
                     startGate.await();
                     benchmarkCallback.task();
                    System.out.println(Thread.currentThread().getName() + "执行");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "线程执行出现异常:" + e.getMessage());
                }finally {
                    endGate.countDown();
                }
            },"Thread-Benchmark-" + i);
            thread.start();
        }

        long start = System.currentTimeMillis();
        System.out.println(Thread.currentThread() + ":All thread is ready,concurrent going...");
        startGate.countDown();
        endGate.await();
        System.out.println(Thread.currentThread() + ":All thread is completed,耗时" + (System.currentTimeMillis() - start) + "ms");
    }

//    public void test1() throws IOException {
//        long start = System.currentTimeMillis();
//        CyclicBarrier barrier = new CyclicBarrier(CONCURRENCY_LEVEL,() ->  System.out.println(Thread.currentThread() + ":All thread is completed,耗时" + (System.currentTimeMillis() - start) + "ms"));
//
//        for (int i = 0; i < CONCURRENCY_LEVEL; i++) {
//            new Thread(() -> {
//                try {
//                    System.out.println(Thread.currentThread() + " has arrived,ready go...");
//                    barrier.await();
//                    benchmarkCallback.task();
//                    System.out.println(Thread.currentThread().getName() + "执行");
//                } catch (Exception e) {
//                    System.out.println(Thread.currentThread().getName() + "线程执行出现异常:" + e.getMessage());
//                }
//            },"Thread-Benchmark-" + i).start();
//        }
//
//        System.out.println(Thread.currentThread() + ":All thread is completed,耗时" + (System.currentTimeMillis() - start) + "ms");
//    }

    /**
     * 模拟 clientTotal 次请求,同时最大 threadTotal 个并发操作
     * @throws InterruptedException
     */
    public void test1() throws InterruptedException {
        // 请求总数
        int clientTotal = 5000;
        // 同时并发执行的线程数
        int threadTotal = 100;

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 闭锁，实现计数器递减
        final CountDownLatch cdl = new CountDownLatch(clientTotal);

        long start = System.currentTimeMillis();
        for (int i = 0;i < clientTotal;i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    benchmarkCallback.task();
                    System.out.println(Thread.currentThread().getName() + "执行");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "线程执行出现异常:" + e.getMessage());
                }finally {
                    semaphore.release();
                    cdl.countDown();
                }
            });
        }

        cdl.await();
        executorService.shutdown();
        System.out.println(Thread.currentThread() + ":All thread is completed,耗时" + (System.currentTimeMillis() - start) + "ms");
    }

}
