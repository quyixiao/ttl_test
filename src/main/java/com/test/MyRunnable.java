package com.test;

public final class MyRunnable implements Runnable {


    private final Runnable runnable;

    public MyRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public static MyRunnable get(Runnable runnable) {
        return new MyRunnable(runnable);
    }

    @Override
    public void run() {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TppHelpTest10.myThreadLocalNo.remove();
        }

    }
}
