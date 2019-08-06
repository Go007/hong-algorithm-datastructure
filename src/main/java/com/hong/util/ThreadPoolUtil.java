package com.hong.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghong
 * Date 2019-08-06 09:53
 * Description:
 */
public class ThreadPoolUtil {

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    public static void main(String[] args) {
        ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                threadPoolUtil.execute();
            }
        },0,3000);
    }

    public void execute(){
        try {
            // 如果写在这里，则会发生OOM异常 unable to create new native thread
           // ExecutorService executorService = Executors.newFixedThreadPool(6);
            for (int i=0;i<100;i++){
                Thread.sleep(300);
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
            System.out.println("=================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
