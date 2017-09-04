package cn.trusteye.concurrency.event;

import java.util.HashSet;

public class EventSource {
    private HashSet<CustomListener> listeners;
    private CustomEvent<EventSource> event;

    public EventSource() {
        this(new HashSet<>());
    }

    public EventSource(HashSet<CustomListener> listeners) {
        this.listeners = listeners;
    }

    public void addListener(CustomListener listener){
        listeners.add(listener);
    }

    public void deleteListerner(CustomListener listener){
        listeners.remove(listener);
    }

    public HashSet<CustomListener> getListeners() {
        return listeners;
    }

    public void setListeners(HashSet<CustomListener> listeners) {
        this.listeners = listeners;
    }

    public void modify(CustomEvent<EventSource> event){
        this.event = event;
        this.event.setSource(this);
        notifyListeners();
    }

    private void notifyListeners() {
        for(CustomListener listener:listeners){
            listener.handle(event);
        }
    }

}
