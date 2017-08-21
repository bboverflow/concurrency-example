package cn.trusteye.concurrency.chapter7;


public class ReadWriteClient {

    public static void main(String[] args) {
        final ShareResource shareResource = new ShareResource(10);

        new ReadWorker("READ_1",shareResource).start();
        new ReadWorker("READ_2",shareResource).start();
        new ReadWorker("READ_3",shareResource).start();
        new ReadWorker("READ_4",shareResource).start();
        new WriteWorker("WRITE_1",shareResource,"abcd").start();
    }
}
