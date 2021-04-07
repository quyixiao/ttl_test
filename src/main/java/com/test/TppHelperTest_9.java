package com.test;

import ch.qos.logback.classic.Logger;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class TppHelperTest_9 {

    private static ExecutorService pool = Executors.newFixedThreadPool(1);



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                String logNo = OrderUtil.getUserPoolOrder("rn");
                Logger.inheritableThreadLocalNo.set(logNo);
                Logger.inheritableThreadLocalTime.set(System.currentTimeMillis());



                log.info("执行复杂逻辑  xxxx " +i);
                log.info("执行复杂逻辑  yyyy " +i);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Logger.inheritableThreadLocalNo.remove();
                Logger.inheritableThreadLocalTime.remove();
            }
        }
    }
}
