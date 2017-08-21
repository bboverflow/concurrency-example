package cn.trusteye.concurrency.chapter7;

import java.util.Random;

public class ReadWorker extends Thread {
    private static final Random random = new Random(System.currentTimeMillis());
    private ShareResource shareResource = new ShareResource();


    public ReadWorker(String name, ShareResource shareResource) {
        super(name);
        this.shareResource = shareResource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = shareResource.read();
                System.out.println(Thread.currentThread().getName()+" reads "+String.valueOf(readBuf));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
