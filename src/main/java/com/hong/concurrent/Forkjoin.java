package com.hong.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by John on 2019/1/24.
 * 场景：一个app首页需要展示用户的基本信息，用户积分，用户余额
 * 且这二部份信息需要调用二个不同的服务获取，相互之间没有依赖关系
 * 假设获取用户基本信息耗时：2s，用户积分3s
 * 如果使用传统的串行获取，则总耗时会超过5s
 * 采取并行获取，则总耗时3s多些
 */
public class Forkjoin {

    /**
     * 获取首页信息
     * @return
     */
    public String getIndexPageInfo() throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();
        /**
         * Callable Future机制
         * Runnable 与 Callable的区别：
         * 1.Runnable无返回值，Callable有返回值
         * 2.Runnable的run()方法不会抛出异常，Callable的call()方法会throws Exception
         * 两者之间的联系：Callable的call()实际运行在Runnable的run()方法里面
         */
        // 1.利用Callable包装业务代码
        Callable<String> userInfoCallable = () -> {
            long start = System.currentTimeMillis();
            String userInfo = "用户基本信息";
            //模拟接口调用耗时
            Thread.sleep(2000);
            System.out.println(Thread.currentThread() +"->获取用户信息耗时：" + (System.currentTimeMillis() - start) + "ms");
            return userInfo;
        };

        Callable<String> integralCallable = () -> {
            long start = System.currentTimeMillis();
            String userInfo = "用户积分";
            //模拟接口调用耗时
            Thread.sleep(3000);
            System.out.println(Thread.currentThread() + "->获取用户积分耗时：" + (System.currentTimeMillis() - start) + "ms");
            return userInfo;
        };

        //多线程异步运行
        FutureTask<String> userInfoTask = new FutureTask<>(userInfoCallable);
        FutureTask<String> integralTask = new FutureTask<>(integralCallable);
       /* MyFutureTask<String> userInfoTask = new MyFutureTask<>(userInfoCallable);
        MyFutureTask<String> integralTask = new MyFutureTask<>(integralCallable);*/
        new Thread(userInfoTask).start();
        new Thread(integralTask).start();

        // 合并结果
        String result = userInfoTask.get() + "||" + integralTask.get();
        System.out.println(Thread.currentThread() + "->总耗时：" + (System.currentTimeMillis() - begin) + "ms");
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Forkjoin forkjoin = new Forkjoin();
        System.out.println(forkjoin.getIndexPageInfo());
    }
}
