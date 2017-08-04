package cn.trusteye.concurrency.java8.chapter1;

import java.io.IOException;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(()->System.out.println(Thread.currentThread().getName())).start();

    }
}
