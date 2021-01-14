package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelperTest_error2 {

    public static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String logNo = OrderUtil.getUserPoolOrder("rn");
                    System.out.println("-------------logNo------" + logNo);
                    threadLocal.set(logNo);
                    try {
                        pool.execute(new MycallableA());
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
            try {
                double a = Math.random()* 1000;
                Thread.sleep((int)a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MycallableA->" + threadLocal.get());
        }
    }


}
