package cn.trusteye.concurrency.producerconsumer.tradition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private ShareResource shareResource;
    private String name;

    public Producer(ShareResource shareResource, String name) {
        this.shareResource = shareResource;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            shareResource.put(name);
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
