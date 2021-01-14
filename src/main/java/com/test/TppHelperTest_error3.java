package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelperTest_error3 {

    public static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for(int i = 0;i < 10;i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String logNo = OrderUtil.getUserPoolOrder("rn");
                    System.out.println("-------------logNo------" + logNo);
                    threadLocal.set(logNo);
                    try {
                        TtlExecutors.getTtlExecutorService(pool).execute(TtlRunnable.get(new MycallableA()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("===Main异常==");
                    }
                }
            }).start();
        }
    }

    static class MycallableA implements Runnable {
        @Override
        public void run() {
            System.out.println("MycallableA->" + threadLocal.get());
        }
    }


}
