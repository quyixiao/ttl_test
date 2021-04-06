package com.test;

import java.util.Random;

public class TppHelperTest_error000 {

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

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
                new Thread(new MycallableA(i)).start();
            }
        }).start();
    }

    static class MycallableA implements Runnable {
        private int i;

        public MycallableA(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i = " + i + "  MycallableA->" + inheritableThreadLocal.get());
        }
    }


}
