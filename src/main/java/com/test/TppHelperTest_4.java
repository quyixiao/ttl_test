package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TppHelperTest_4 {

    public static TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();


    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                future.complete("返回任务结果");
            }
        }).start();
        System.out.println(future.get());
    }

}
