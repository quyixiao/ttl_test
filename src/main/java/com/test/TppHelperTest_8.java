package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelperTest_8 {

    public static TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();
    // public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            startThread(i);
        }
    }

    public static void startThread(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String logNo = OrderUtil.getUserPoolOrder("rn");
                System.out.println("i = " + i + "   使用 主线程的线程编号 ： " + logNo);
                inheritableThreadLocal.set(logNo);
                TtlExecutors.getTtlExecutorService(pool).submit(TtlCallable.get(new MycallableA(i)));
            }
        }).start();
    }

    static class MycallableA implements Callable {
        private int i;

        public MycallableA(int i) {
            this.i = i;
        }


        @Override
        public Object call() throws Exception {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i = " + i + "  MycallableA->" + inheritableThreadLocal.get());
            return null;
        }
    }


}
