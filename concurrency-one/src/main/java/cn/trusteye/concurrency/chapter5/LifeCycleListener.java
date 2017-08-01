package cn.trusteye.concurrency.chapter5;

public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
