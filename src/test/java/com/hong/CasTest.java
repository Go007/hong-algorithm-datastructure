package com.hong;

import com.hong.cas.Counter;
import com.hong.cas.CounterLock;
import com.hong.cas.CounterUnsafe;
import com.hong.lock.SpinLockDemo2;
import org.junit.Test;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/16 11:24
 * @Version V1.0
 **/
public class CasTest {

    @Test
    public void testCounter() throws Exception{
        Counter counter = new Counter();
        BenchmarkCallback task = () -> counter.increment();
        Benchmark benchmark = new Benchmark(5000,task);
        benchmark.test();
        System.out.println(counter.i);
    }

    @Test
    public void testCounterUnsafe() throws Exception{
        CounterUnsafe counterUnsafe = new CounterUnsafe();
        BenchmarkCallback task = () -> counterUnsafe.increment();
        Benchmark benchmark = new Benchmark(5000,task);
        benchmark.test();
        System.out.println(counterUnsafe.i);
    }

    @Test
    public void testLock() throws Exception{
        CounterLock counterLock = new CounterLock(new SpinLockDemo2());
        BenchmarkCallback task = () -> counterLock.increment();
        Benchmark benchmark = new Benchmark(5000,task);
        benchmark.test();
        System.out.println(counterLock.i);
    }

}
