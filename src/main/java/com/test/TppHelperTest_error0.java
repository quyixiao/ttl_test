package com.test;

public class TppHelperTest_error0 {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String logNo = OrderUtil.getUserPoolOrder("rn");
            System.out.println("i = " + i + "  主线程的线程编号 ：" + logNo);
            threadLocal.set(logNo);
            threadTest(i);
        }
    }

    public static void threadTest(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i = " + i + "在子线程中获取线程编号 " + threadLocal.get());
            }
        }).start();
    }


}
