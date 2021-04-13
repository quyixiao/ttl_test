package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TppHelpTest10 {

    public static final ThreadLocal<String> threadLocalNo = new ThreadLocal();
    public static final ThreadLocal<String> myThreadLocalNo = new ThreadLocal();
    public static final TransmittableThreadLocal<Long> inheritableThreadLocalTime = new TransmittableThreadLocal();
    public static final TransmittableThreadLocal<String> inheritableThreadLocalNo = new TransmittableThreadLocal();

    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static String getPre(){
        if (inheritableThreadLocalNo != null && inheritableThreadLocalNo.get() != null) {
            StringBuffer sb = new StringBuffer();
            String threadNo = threadLocalNo.get();
            if (threadNo == null || threadNo.length() == 0) {
                if(myThreadLocalNo.get() ==null  ){
                    myThreadLocalNo.set(OrderUtil.getUserPoolOrder("cr"));
                }
                threadNo = myThreadLocalNo.get();
            } else {
                myThreadLocalNo.remove();
            }
            sb.append("[").append(inheritableThreadLocalNo.get()).append(",").append(threadNo).append("]").append("\t");
            Long start = inheritableThreadLocalTime.get();
            Long end;
            if (start != null) {
                end = System.currentTimeMillis();
                sb.append("exet=").append(end - start).append("\t");
            }
            return sb.toString();
        } else {
            return "";
        }


    }
    public static void main(String[] args) {
        String logNo = OrderUtil.getUserPoolOrder("tr");
        long start = System.currentTimeMillis();

        threadLocalNo.set(logNo);
        inheritableThreadLocalNo.set(logNo);
        inheritableThreadLocalTime.set(start);

        System.out.println(getPre() + "主线程测试");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(getPre() + "xxxxxx");
                System.out.println(getPre() + "xxxxxx1");
                System.out.println(getPre() + "xxxxxx2");
                System.out.println(getPre() + "xxxxxx3");
                System.out.println(getPre() + "xxxxxx4");
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(getPre() + "yyyyyyy");
                System.out.println(getPre() + "yyyyyyy1");
                System.out.println(getPre() + "yyyyyyy2");
                System.out.println(getPre() + "yyyyyyy3");
                System.out.println(getPre() + "yyyyyyy4");
            }
        }).start();


       for(int i = 0 ;i < 5 ; i ++){
           startThread(i);
       }

    }


    public static void startThread(final int i) {
        TtlExecutors.getTtlExecutorService(pool).submit(TtlRunnable.get(MyRunnable.get(new MycallableA(i))));
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
            System.out.println(getPre() + "i = " + i + "  MycallableA->" );
            System.out.println(getPre() + "i = " + i + "  MycallableA->" );
            System.out.println(getPre() + "i = " + i + "  MycallableA->" );
            System.out.println(getPre() + "i = " + i + "  MycallableA->" );
            System.out.println(getPre() + "i = " + i + "  MycallableA->" );
        }
    }

}
