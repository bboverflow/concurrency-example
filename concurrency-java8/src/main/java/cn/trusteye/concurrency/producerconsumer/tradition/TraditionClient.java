package cn.trusteye.concurrency.producerconsumer.tradition;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TraditionClient {


    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource(3);

        /*
        new Thread(new Consumer(shareResource),"Consumer_1").start();
        new Thread(new Consumer(shareResource),"Consumer_2").start();

        new Thread(new Producer(shareResource),"Procucer_1").start();
        new Thread(new Producer(shareResource),"Procucer_2").start();
        new Thread(new Producer(shareResource),"Procucer_3").start();
*/
        //线程池
        ExecutorService producers = Executors.newFixedThreadPool(3);
        producers.execute(new Producer(shareResource,"producer-1"));
        producers.execute(new Producer(shareResource,"producer-2"));
        producers.execute(new Producer(shareResource,"producer-3"));
        ExecutorService consumers = Executors.newFixedThreadPool(2);
        consumers.execute(new Consumer(shareResource,"consumer-1"));
        consumers.execute(new Consumer(shareResource,"consumer-2"));



    }
}
