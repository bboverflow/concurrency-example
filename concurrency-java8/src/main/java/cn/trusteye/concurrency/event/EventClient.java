package cn.trusteye.concurrency.event;

public class EventClient {
    public static void main(String[] args) {
        EventSource source = new EventSource();
        source.addListener(new CustomListener("listener1"));
        source.addListener(new CustomListener("listener2"));
        source.addListener(new CustomListener("listener3"));

        CustomEvent event = new CustomEvent(source, "休息");
        source.modify(event);
        event.setMessage("上班");
        source.modify(event);
    }
}
