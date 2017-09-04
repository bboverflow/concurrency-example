package cn.trusteye.concurrency.producerconsumer.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class queueClient {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource(10);
        //线程池
        ExecutorService producers = Executors.newFixedThreadPool(5);
        producers.execute(new Producer(shareResource,"producer-1"));
        producers.execute(new Producer(shareResource,"producer-2"));
        producers.execute(new Producer(shareResource,"producer-3"));
        producers.execute(new Producer(shareResource,"producer-4"));
        producers.execute(new Producer(shareResource,"producer-5"));
        ExecutorService consumers = Executors.newFixedThreadPool(5);
        consumers.execute(new MokeTask());
        consumers.execute(new MokeTask());
        consumers.execute(new Consumer(shareResource,"consumer-1"));
        consumers.execute(new Consumer(shareResource,"consumer-2"));
        consumers.execute(new Consumer(shareResource,"consumer-3"));
        consumers.execute(new Consumer(shareResource,"consumer-4"));
        consumers.execute(new Consumer(shareResource,"consumer-5"));

    }
}
