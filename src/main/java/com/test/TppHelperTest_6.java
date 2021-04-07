package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TppHelperTest_6 {

    public static TransmittableThreadLocal<String> inheritableThreadLocal = new TransmittableThreadLocal<>();


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            a(i);
        }
    }

    public static void a(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                b(i);
            }
        }).start();
    }

    public static void b(int i) {
        List<String> a = new ArrayList<>();
        String logNo = OrderUtil.getUserPoolOrder("rn");
        inheritableThreadLocal.set(logNo);
        System.out.println("i = " + i + " 主线程日志编号 =" + inheritableThreadLocal.get());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletableFuture<List<String>> listCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return list(i,1);
        }, executor);
        CompletableFuture<List<String>> listCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            return list(i,4);
        }, executor);
        CompletableFuture<List<String>> listCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            return list(i,7);
        }, executor);
        try {
            addAll(a, listCompletableFuture);
            addAll(a, listCompletableFuture1);
            addAll(a, listCompletableFuture2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addAll(List<String> a, CompletableFuture<List<String>> listCompletableFuture) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a.addAll(listCompletableFuture.get(2000, TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static List<String> list(int i, int j) {
        Random random = new Random();
        try {
            int k = random.nextInt(1000);
            Thread.sleep(k);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i = " + i + " 子线程日志编号 =" + inheritableThreadLocal.get());
        List<String> list = new ArrayList<>();
        for (int z = 0 ; z < j + 3; z ++) {
            list.add(z + "");

        }
        return list;
    }


}
