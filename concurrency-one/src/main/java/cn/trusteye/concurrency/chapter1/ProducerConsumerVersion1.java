package cn.trusteye.concurrency.chapter1;

/**
 * Created by Rayman on 2017/7/24.
 */
public class ProducerConsumerVersion1 {

    private static int i = 0;
    final private Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK){
            i++;
            System.out.println("P->"+i);
        }
    }
    private void consume(){
        synchronized (LOCK){
            i--;
            System.out.println("C->"+i);
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();
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
