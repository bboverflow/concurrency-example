package cn.trusteye.concurrency.immutable;

public class ImmutablePerformance {
    public static void main(String[] args) throws InterruptedException {
        //immutable 6673
        //sync 10616
        ImmutableObject object = new ImmutableObject("张三",25);
        //SyncObject object = new SyncObject("张三",25);
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println(Thread.currentThread().getName()+" "+object);
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println(Thread.currentThread().getName()+" "+object);
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println(Thread.currentThread().getName()+" "+object);
                }
            }
        });
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(System.currentTimeMillis()-start);
    }
}

class ImmutableObject{
    private final String name;
    private final int age;

    public ImmutableObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ImmutableObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class SyncObject{
    private String name;
    private int age;

    public SyncObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized int getAge() {
        return age;
    }

    @Override
    public synchronized String toString() {
        return "SyncObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
