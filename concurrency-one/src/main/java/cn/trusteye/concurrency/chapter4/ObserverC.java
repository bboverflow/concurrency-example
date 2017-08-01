package cn.trusteye.concurrency.chapter4;

public class ObserverC extends Observer {

    @Override
    public void update() {
        System.out.println("ObserverC get status:"+subject.getStatus());
    }
}
