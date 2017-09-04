package cn.trusteye.concurrency.event;

import java.util.EventObject;

public class CustomEvent<T> extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    private T source;
    private String message;

    public CustomEvent(T source, String message) {
        super(source);
        this.message = message;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "source=" + source +
                ", message='" + message + '\'' +
                '}';
    }
}
