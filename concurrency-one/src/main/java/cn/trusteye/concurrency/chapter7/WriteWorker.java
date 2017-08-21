package cn.trusteye.concurrency.chapter7;

import java.util.Random;

public class WriteWorker extends Thread {
    private static final Random random = new Random(System.currentTimeMillis());

    private ShareResource shareResource = new ShareResource();

    private final String filler;

    private int index = 0;

    public WriteWorker(String name, ShareResource shareResource, String filler) {
        super(name);
        this.shareResource = shareResource;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                shareResource.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private char nextChar(){
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index=0;
        }
        return c;
    }
}
