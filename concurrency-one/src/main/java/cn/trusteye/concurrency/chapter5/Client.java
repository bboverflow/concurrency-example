package cn.trusteye.concurrency.chapter5;

public class Client {

    public static void main(String[] args) {
        ThreadObserver observer = new ThreadObserver();


        Thread t1 = new Thread(new ObservableRunnable(observer) {
            @Override
            public void run() {
                notify(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                try {
                    Thread.sleep(1000L);
                    int i=1/0;
                } catch (Exception e) {
                    notify(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                }
                notify(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
            }
        }, "thread1");
        t1.start();


        Thread t2 = new Thread(new ObservableRunnable(observer) {
            @Override
            public void run() {
                notify(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notify(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
            }
        }, "thread2");
        t2.start();

    }

}
