package cn.trusteye.concurrency.chapter1;

/**
 * Created by Rayman on 2017/7/24.
 */
public class ProducerConsumerVersion2 {

    private static int i = 0;
    final private Object LOCK = new Object();
    private static boolean produced = false;

    private void produce(){
        synchronized (LOCK){
            if(produced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                i++;
                System.out.println("P->"+i);
                produced = true;
                LOCK.notify();
            }
        }
    }

    private void consume()  {
        synchronized (LOCK){
            if(produced) {
                i--;
                System.out.println("C->" + i);
                produced = false;
                LOCK.notify();
            }
            else{
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion2 pc = new ProducerConsumerVersion2();
        new Thread("P") {
            @Override
            public void run() {
                while(true)
                    pc.produce();
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while(true)
                    pc.consume();
            }
        }.start();

    }
}
