package cn.trusteye.concurrency.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClient {
    private static volatile int a = 0;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger ai = new AtomicInteger(0);
        Object lock = new Object();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
//                    System.out.println(Thread.currentThread().getName()+" "+ai.addAndGet(1));
                        a=a+1;
                        System.out.println(Thread.currentThread().getName() + " " + a);
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
//                    System.out.println(Thread.currentThread().getName()+" "+ai.addAndGet(1));
                        a=a+1;
                        System.out.println(Thread.currentThread().getName() + " " + a);
                }
            }
        }.start();

        TimeUnit.SECONDS.sleep(1000);


    }
}
