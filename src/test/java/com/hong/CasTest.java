package com.hong;

import com.hong.cas.Counter;
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
        System.out.println(Counter.i);
    }

}
