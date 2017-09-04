package cn.trusteye.concurrency.producerconsumer.tradition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResource {
    private static int number;
    private static LinkedList<String> list = new LinkedList<>();
    private static int max_value = 10;


    private static Lock lock = new ReentrantLock();
    private static Condition readCondition = lock.newCondition();
    private static Condition writeCondition = lock.newCondition();

    public ShareResource(int max_value) {
        this.max_value = max_value;
    }

    public void put(String user) {
        try {
            lock.lock();
            while (list.size() == max_value) {
                writeCondition.await();
            }
            list.add(Integer.toString(number));
            System.out.println(Thread.currentThread().getName() + " +++++++ " + user + " ++++++++ " + number++);
            readCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void get(String user) {
        try {
            lock.lock();
            while (list.size() == 0) {
                readCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + " ------- " + user + " -------- " + list.remove());
            ;
            writeCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
