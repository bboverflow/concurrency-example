package cn.trusteye.concurrency.chapter7;

public class ReadWriteLock {
    private int readingReaders = 0;
    private int waitingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException{
        waitingReaders++;
        try {
            while (writingWriters > 0 || (preferWriter && waitingWriters >0)) {
                wait();
            }
            readingReaders++;
        }finally {
           waitingReaders--;
        }
    }

    public synchronized void readUnlock(){
        readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException{
        waitingWriters++;
        try{
            while(readingReaders > 0 || writingWriters > 0 )
                wait();
            writingWriters++;
        }finally {
            waitingWriters--;
        }
    }

    public synchronized void writeUnlock(){
        writingWriters--;
        this.notifyAll();
    }
}
