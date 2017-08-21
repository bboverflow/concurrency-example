package cn.trusteye.concurrency.chapter7;


public class ShareResource {
    private static volatile ReadWriteLock LOCK;
    private char[] resource;
    private static  int DEFAULT_SIZE = 10;

    static {
        LOCK = new ReadWriteLock();
    }

    public ShareResource() {
        this(DEFAULT_SIZE);
    }

    public ShareResource(int size) {
        this.resource = new char[size];
        for (int i = 0; i < resource.length; i++) {
            this.resource[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            LOCK.readLock();
            return doRead();
        }finally {
            LOCK.readUnlock();
        }
    }

    private char[] doRead() {
        char[] newBuf = new char[resource.length];
        for (int i = 0; i < resource.length; i++) {
            newBuf[i] = resource[i];
        }
        slowly(50);
        return newBuf;
    }

    private void slowly(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            LOCK.writeLock();
            doWrite(c);
        }
        finally {
            LOCK.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < resource.length; i++) {
            resource[i] = c;
        }
        slowly(50);
    }
}
