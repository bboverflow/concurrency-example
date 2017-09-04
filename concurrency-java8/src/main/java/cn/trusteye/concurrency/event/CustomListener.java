package cn.trusteye.concurrency.event;

import java.util.EventListener;

public class CustomListener implements EventListener,Comparable{
    private String name;

    public CustomListener(String name) {
        this.name = name;
    }

    public void handle(CustomEvent event){
        System.out.println(name+" 收到消息 "+event);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((CustomListener)o).getName());
    }
}
