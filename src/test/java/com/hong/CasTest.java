package com.hong;

import com.hong.cas.Counter;
import com.hong.cas.CounterUnsafe;
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

}
