package cn.trusteye.concurrency.chapter4;

public class ObserverA extends Observer {

    @Override
    public void update() {
        System.out.println("ObserverA get status:"+subject.getStatus());
    }
}
