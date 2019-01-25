package com.hong.concurrent;

import org.joda.time.DateTime;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by John on 2019/1/25.
 * 使用OpenJDK的JMH工具进行性能压测
 * Java Microbenchmark Harness，是专门用于代码微基准测试的工具套件
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)  // 输出结果的时间粒度为纳秒
@BenchmarkMode(Mode.AverageTime) // 测试方法平均执行时间
//@State(Scope.Thread) // 每个测试线程一个实例
public class JmhMain {
    /**
     * 分别对Calendar ,Joda Time,Java8的时间api进行压测
     */
    public static void main(String[] args) throws Exception {
        // 使用一个单独进程执行测试
        Options options = new OptionsBuilder().include(JmhMain.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(5)
    public void runCalendar() {
        Calendar calendar = Calendar.getInstance();
    }

    @Benchmark
    @Threads(5)
    public void runJoda() {
        DateTime dateTime = new DateTime();
    }

    @Benchmark
    @Threads(5)
    public void runJdk8time() {
        LocalDate localDate = LocalDate.now();
    }
}
