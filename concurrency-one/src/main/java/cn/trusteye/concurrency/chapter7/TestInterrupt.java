package cn.trusteye.concurrency.chapter7;

public class TestInterrupt {
    public static void main(String[] args) {

        Thread t1 = new InterrupThread();
        t1.start();
        t1.interrupt();
    }
}

class InterrupThread extends Thread{
    @Override
    public void run() {
        try {
            doSomthing();
        } catch (InterruptedException e) {
        }
    }

    private void doSomthing() throws InterruptedException {
        synchronized (this){
            try {
                System.out.println("before wait");
                wait();
                System.out.println("after wait");

            }finally {
                System.out.println("finally wait");
            }
        }
    }
}