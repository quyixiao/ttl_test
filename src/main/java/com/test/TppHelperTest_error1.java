package com.test;

public class TppHelperTest_error1 {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String logNo = OrderUtil.getUserPoolOrder("rn");
                    System.out.println("-------------logNo------" + logNo);
                    threadLocal.set(logNo);
                    try {
                        TppHelper.getPool().execute(new MycallableA());
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
