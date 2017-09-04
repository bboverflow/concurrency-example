package cn.trusteye.concurrency.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureClient {
    public static void main(String[] args) throws InterruptedException {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return new Random(System.currentTimeMillis()).nextInt(100);
            }
        };

        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();

        try {
            try {
                System.out.println(future.get(1L,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
