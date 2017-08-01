package cn.trusteye.concurrency.chapter2;

import java.util.Arrays;

/**
 * Created by Rayman on 2017/7/26.
 */
public class CaptureService {
    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {

        Thread t = new WorkThread();
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getStackTrace().toString());
                System.out.println(e);
            }
        });
        t.start();
    }

    static class WorkThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                int result = A/B;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
