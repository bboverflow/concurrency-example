package cn.trusteye.concurrency.chapter6;

public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User user1 = new User("shanghairen", "shanghai",gate);
        User user2 = new User("beijingren", "beijing",gate);
        User user3 = new User("guangzhouren", "guangzhou",gate);

        user1.start();
        user2.start();
        user3.start();
    }
}
