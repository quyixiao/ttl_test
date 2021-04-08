package com.test;

import org.slf4j.MDC;

public class ThreadTest extends Thread {
    private int i ;  
      
    public ThreadTest(){  
    }  
      
    public ThreadTest(int i){  
        this.i = i;  
    }  
      
    public void run(){  
        System.out.println(++i);  
        MDC.put("username", i + "");
      
        for (int j = 0; j < 10; j++) {
            System.out.println("aaa" + MDC.get("username"));
            if(j==5){
                try {  
                    this.sleep(1000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        System.out.println("run: " + i + "     "  + MDC.get("username"));  
    }  
      
    public static void main(String args[]) throws InterruptedException{  
        ThreadTest t1 = new ThreadTest(1);  
        t1.start();  
        ThreadTest t2 = new ThreadTest(2);  
        t2.start();  
    }
}  