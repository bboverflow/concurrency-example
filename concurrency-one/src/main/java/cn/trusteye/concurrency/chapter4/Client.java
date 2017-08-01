package cn.trusteye.concurrency.chapter4;

public class Client {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observerA = new ObserverA();
        Observer observerB = new ObserverB();
        Observer observerC = new ObserverC();
        subject.attachObserver(observerA);
        subject.attachObserver(observerB);
        subject.attachObserver(observerC);

        subject.setStatus(0);
        System.out.println("=================");
        subject.setStatus(100);
        System.out.println("=================");
        subject.setStatus(9999);
    }
}
