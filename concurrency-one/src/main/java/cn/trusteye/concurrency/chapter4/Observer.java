package cn.trusteye.concurrency.chapter4;

public abstract class Observer {
    Subject subject;

    void setSubject(Subject subject){
        this.subject = subject;
    }
    public abstract void update();
}
