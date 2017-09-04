package cn.trusteye.concurrency.disruptor.multiProducer;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MultiProducerClient {
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        int producerNum = 10;
        //创建ringBuffer
        RingBuffer<Order> ringBuffer =
                RingBuffer.create(ProducerType.MULTI,
                        new EventFactory<Order>() {
                            @Override
                            public Order newInstance() {
                                return new Order();
                            }
                        },
                        bufferSize,
                        new YieldingWaitStrategy());

        SequenceBarrier barriers = ringBuffer.newBarrier();

        //3个消费者
        Consumer[] consumers = new Consumer[3];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("c" + i);
        }

        WorkerPool<Order> workerPool =
                new WorkerPool<Order>(ringBuffer,
                        barriers,
                        new IntEventExceptionHandler(),
                        consumers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        ExecutorService executor = Executors.newFixedThreadPool(4);
        workerPool.start(executor);

        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < producerNum; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                    }
                    for (int j = 0; j < 1; j++) {
                        p.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("---------------开始生产-----------------");
        latch.countDown();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("总数:" + consumers[0].getCount());

        workerPool.halt();
        executor.shutdown();
    }

    static class IntEventExceptionHandler implements ExceptionHandler {

        @Override
        public void handleEventException(Throwable throwable, long l, Object o) {

        }

        @Override
        public void handleOnStartException(Throwable throwable) {

        }

        @Override
        public void handleOnShutdownException(Throwable throwable) {

        }
    }
}


