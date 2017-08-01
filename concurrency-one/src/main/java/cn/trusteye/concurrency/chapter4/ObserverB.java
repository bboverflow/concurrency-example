package cn.trusteye.concurrency.chapter4;

public class ObserverB extends Observer {

    @Override
    public void update() {
        System.out.println("ObserverB get status:"+subject.getStatus());
    }
}
