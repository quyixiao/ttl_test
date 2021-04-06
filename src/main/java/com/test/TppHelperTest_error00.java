package com.test;

public class TppHelperTest_error00 {

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        for(int i =0 ;i < 10 ;i ++){
            String logNo = OrderUtil.getUserPoolOrder("rn");
            System.out.println("使用 主线程的线程编号 ：i =" + i + " ，日志编号 ："  + logNo);
            inheritableThreadLocal.set(logNo);
        }
    }


}
