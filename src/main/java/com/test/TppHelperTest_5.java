package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelperTest_5 {

    public static TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            test(i);
        }

    }

    public static void test(final int i) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CompletableFuture<String> future = new CompletableFuture<>();
                    String logNo = OrderUtil.getUserPoolOrder("rn");
                    System.out.println("i= " + i + "  主线程编号 ：" + logNo);
                    inheritableThreadLocal.set(logNo);
                    childThread(i, future);
                    System.out.println("i= " + i + " ,返回结果 ：" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void childThread(final int i, CompletableFuture<String> future)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int c = random.nextInt(1000);
                try {
                    Thread.sleep(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i + " 子线程编号 ：" + inheritableThreadLocal.get());
                future.complete("返回任务结果" + i);
            }
        }).start();
    }

}
