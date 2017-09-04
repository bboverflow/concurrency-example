package cn.trusteye.concurrency.immutable;

import java.util.stream.IntStream;

public class PersonClient {
    public static void main(String[] args) {
        Person person = new Person("Tom", 20);
        IntStream.range(0,5).forEach(i->new PersonThread(person).start());
    }
}
