package cn.trusteye.concurrency.producerconsumer.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ShareResource {
    ArrayBlockingQueue<Integer> shareResource = new ArrayBlockingQueue<Integer>(3);
    private static volatile AtomicInteger number = new AtomicInteger(0);
    private static int max_value = 10;


    public ShareResource(int max_value) {
        this.max_value = max_value;
    }

    public void put(String user) {
        try {
            shareResource.put(number.incrementAndGet());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
        }
    }

    public void get(String user) {
        try {
            System.out.println(Thread.currentThread().getName() + " ------- " + user + " ----------- " + shareResource.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
