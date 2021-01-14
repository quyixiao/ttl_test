package com.test;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelper {

    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static ExecutorService getPool() {
        return TtlExecutors.getTtlExecutorService(pool);


    }
}