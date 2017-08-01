package cn.trusteye.concurrency.chapter4;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> observers = new ArrayList<>();
    int status = 0;

    void attachObserver(Observer observer){
        if(!observers.contains(observer)) {
            this.observers.add(observer);
            observer.setSubject(this);
        }
    }

    public void setStatus(int status){
        if(this.status!= status)
            this.status = status;
        notifyAllObserver();
    }

    public int getStatus(){
        return this.status;
    }

    void notifyAllObserver(){
        for(Observer observer:observers){
            observer.update();
        }
    }
}
