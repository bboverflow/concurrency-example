package cn.trusteye.concurrency.chapter5;

public class ThreadObserver implements LifeCycleListener {
    Object lock = new Object();

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (lock) {
            System.out.println("线程"+event.getThread()+"状态:"+event.getState());
            if (event.getState().equals(ObservableRunnable.RunnableState.ERROR))
                System.out.println(event.getCause());
        }
    }
}
